package com.luter.macaw.jpa.generator;


import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.JdbcType;
import com.luter.heimdall.starter.utils.json.JacksonUtils;
import com.luter.macaw.jpa.generator.config.TableModel;
import com.luter.macaw.jpa.generator.util.DbMetaUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Data
@Accessors(chain = true)
public class MysqlGenerator {

    private String driverClass;
    private String url;
    private String username;
    private String password;
    private String[] baseColumns;
    private String[] tablePrefix;
    private String destBasePath;
    private String destBasePkgPath;
    private String moduleName;
    private String[] tables;
    private String[] cType;
    private String[] moduleDesc;

    private String baseController;
    private String baseService;
    private String baseServiceImpl;
    private String baseDTO;
    private String baseVO;
    private String baseEntity;


    public List<TableModel> getTableModels(String tableName) {
        List<TableModel> models = new ArrayList<>();
        Connection connection = null;
        ResultSet colRet = null;
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, username, password);
            ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null);
            if (!rs.next()) {
                System.err.println("错误:表  " + tableName + "  不存在");
                System.exit(1);
            }
            colRet = connection.getMetaData().getColumns(DbMetaUtil.getDbNameByUrl(url), "%", tableName, "%");
            while (colRet.next()) {
                System.out.println("字段名：" + colRet.getString("COLUMN_NAME") + "--字段注释：" + colRet.getString("REMARKS") + "--字段数据类型：" + colRet.getString("TYPE_NAME"));
                String columnName = colRet.getString("COLUMN_NAME");
                String remark = colRet.getString("REMARKS");
                String type = colRet.getString("TYPE_NAME");
                String columnNameCamel = columnName;
                //boolean类型处理,把is_去掉
                if (JdbcType.BIT.name().equals(type) && columnName.contains("is_")) {
                    columnNameCamel = columnName.replace("is_", "");
                }
                columnNameCamel = StrUtil.toCamelCase(columnNameCamel);
                TableModel a = new TableModel();
                a.setTName(columnName);
                a.setCName(columnNameCamel);
                a.setRemarks(remark);
                a.setCType(type);
                //排除公共类字段
                if (!ArrayUtil.contains(baseColumns, columnName)) {
                    models.add(a);
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
            if (null != colRet) {
                try {
                    colRet.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return models;

    }

    public void genTablesCode() {
        for (int i = 0; i < tables.length; i++) {
            String mDesc = StrUtil.isEmpty(moduleDesc[i]) ? "" : moduleDesc[i];
            genCode(tables[i], mDesc);
        }

    }

    public void genCode(String tableName, String mDesc) {
        System.out.println("开始处理表:" + tableName + "," + mDesc);
        String controllerOutPath = destBasePath + File.separator + moduleName + File.separator + "controller" + File.separator;
        String dtoOutPath = destBasePath + File.separator + moduleName + File.separator + "dto" + File.separator;
        String mapperOutPath = destBasePath + File.separator + moduleName + File.separator + "mapper" + File.separator;
        String voOutPath = destBasePath + File.separator + moduleName + File.separator + "vo" + File.separator;
        String entityOutPath = destBasePath + File.separator + moduleName + File.separator + "entity" + File.separator;
        String repoOutPath = destBasePath + File.separator + moduleName + File.separator + "repository" + File.separator;
        String serviceOutPath = destBasePath + File.separator + moduleName + File.separator + "service" + File.separator;
        String serviceImplOutPath = serviceOutPath + "impl" + File.separator;
        Map<String, Object> data = new HashMap<>(10);

        //二级url ，/模块/类
        String lowerCaseTableName = tableName.toLowerCase();
        String urlName = lowerCaseTableName;
        if (lowerCaseTableName.contains("_")) {
            if (null == tablePrefix || tablePrefix.length <= 0) {
                throw new RuntimeException("表前缀: tablePrefix 必须配置");
            }
            String prefix = tableName.substring(0, tableName.indexOf("_") + 1);
            if (Arrays.asList(tablePrefix).contains(prefix)) {
                urlName = tableName.substring(prefix.length());
            }

        }
        //驼峰大写名字
        String className = StrUtil.upperFirst(moduleName) + StrUtil.upperFirst(StrUtil.toCamelCase(urlName));
        urlName = urlName.toLowerCase().replace("_", "/");
        String pkgPath = destBasePkgPath + "." + moduleName;
        data.put("tablename", tableName);
        data.put("className", className);
        data.put("pkgPath", pkgPath);
        data.put("LFClassName", StrUtil.lowerFirst(className));
        data.put("urlName", "/" + moduleName.toLowerCase() + "/" + urlName);
        data.put("moduleDesc", mDesc);
        //基础类
        data.put("baseController", baseController);
        data.put("baseControllerName", StrUtil.subAfter(baseController, ".", true));
        data.put("baseDTO", baseDTO);
        data.put("baseDTOName", StrUtil.subAfter(baseDTO, ".", true));
        data.put("baseVO", baseVO);
        data.put("baseVOName", StrUtil.subAfter(baseVO, ".", true));
        data.put("baseEntity", baseEntity);
        data.put("baseEntityName", StrUtil.subAfter(baseEntity, ".", true));
        data.put("baseService", baseService);
        data.put("baseServiceName", StrUtil.subAfter(baseService, ".", true));
        data.put("baseServiceImpl", baseServiceImpl);
        data.put("baseServiceImplName", StrUtil.subAfter(baseServiceImpl, ".", true));
        //表字段
        List<TableModel> tableModels = getTableModels(tableName);
        System.out.println(JacksonUtils.toPrettyJson(tableModels));
        data.put("fields", tableModels);
        if (null == cType || cType.length <= 0) {
            genCode("ordController.ftl", data, controllerOutPath, className + "Controller.java");
            genCode("Dto.ftl", data, dtoOutPath, className + "DTO.java");
            genCode("Mapper.ftl", data, mapperOutPath, className + "Mapper.java");
            genCode("Vo.ftl", data, voOutPath, className + "VO.java");
            genCode("Entity.ftl", data, entityOutPath, className + "Entity.java");
            genCode("Repository.ftl", data, repoOutPath, className + "Repository.java");
            genCode("Service.ftl", data, serviceOutPath, className + "Service.java");
            genCode("ServiceImpl.ftl", data, serviceImplOutPath, className + "ServiceImpl.java");
        } else {
            if (ArrayUtil.containsIgnoreCase(cType, "C")) {
                genCode("ordController.ftl", data, controllerOutPath, className + "Controller.java");
            }
            if (ArrayUtil.containsIgnoreCase(cType, "D")) {
                genCode("Dto.ftl", data, dtoOutPath, className + "DTO.java");
            }
            if (ArrayUtil.containsIgnoreCase(cType, "M")) {
                genCode("Mapper.ftl", data, mapperOutPath, className + "Mapper.java");
            }
            if (ArrayUtil.containsIgnoreCase(cType, "V")) {
                genCode("Vo.ftl", data, voOutPath, className + "VO.java");
            }
            if (ArrayUtil.containsIgnoreCase(cType, "E")) {
                genCode("Entity.ftl", data, entityOutPath, className + "Entity.java");
            }
            if (ArrayUtil.containsIgnoreCase(cType, "R")) {
                genCode("Repository.ftl", data, repoOutPath, className + "Repository.java");
            }
            if (ArrayUtil.containsIgnoreCase(cType, "S")) {
                genCode("Service.ftl", data, serviceOutPath, className + "Service.java");
                genCode("ServiceImpl.ftl", data, serviceImplOutPath, className + "ServiceImpl.java");
            }
        }
        System.out.println("表:" + tableName + " [" + mDesc + "]处理完成");
    }

    public void genCode(String tplName, Map<String, Object> data, String outPath, String outFileName) {
        try {
            URL tplBaseUrl = this.getClass().getResource("/");
            if (null == tplBaseUrl) {
                System.err.println("获取模板基础路径失败");
                System.exit(1);
            }
            String tplBasePath = tplBaseUrl.getPath();
            System.out.println("处理模板:" + tplBasePath + tplName);
            // 1、创建个Configuration对象
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
            // 2、设置模板文件所在的路径的目录
            configuration.setDirectoryForTemplateLoading(new File(tplBasePath));
            // 3、设置模板文件的字符集
            configuration.setDefaultEncoding("UTF-8");
            // 4、创建模板文件需要展示数据的数据集对象，可以使用POJO，也可以使用map 一般是使用map
            // 5、首先创建模板文件，再加载模板文件 模板文件的后缀官方统一的标准是.ftl 其实任何类型都行。
            Template templateEntity = configuration.getTemplate(tplName);
            // 6、创建一个FileWriter对象 指定生成的静态文件的文件路径及文件名
            File outFolder = new File(outPath);
            if (!outFolder.exists()) {
                boolean mkdirs = outFolder.mkdirs();
            }
            File file = new File(outPath + File.separator + outFileName);
            FileWriter fileWriter = new FileWriter(file);
            templateEntity.process(data, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
