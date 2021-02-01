
/*
 *
 *  *
 *  *
 *  *      Copyright 2020-2021 Luter.me
 *  *
 *  *      Licensed under the Apache License, Version 2.0 (the "License");
 *  *      you may not use this file except in compliance with the License.
 *  *      You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *      Unless required by applicable law or agreed to in writing, software
 *  *      distributed under the License is distributed on an "AS IS" BASIS,
 *  *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *      See the License for the specific language governing permissions and
 *  *      limitations under the License.
 *  *
 *  *
 *
 */

package com.luter.heimdall.generator.mybatis;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.luter.heimdall.starter.mybatis.base.controller.AbstractMybatisController;
import com.luter.heimdall.starter.mybatis.base.entity.MybatisAbstractEntity;
import com.luter.heimdall.starter.mybatis.base.service.BaseMybatisService;
import com.luter.heimdall.starter.mybatis.base.service.impl.BaseMybatisServiceImpl;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlGenerator {


    private String destPackage;
    private String projectPath;
    private String driverClass;
    private String url;
    private String username;
    private String password;

    public MysqlGenerator setDestPackage(String destPackage) {
        this.destPackage = destPackage;
        return this;
    }

    public MysqlGenerator setProjectPath(String projectPath) {
        this.projectPath = projectPath;
        return this;
    }

    public MysqlGenerator setDriverClass(String driverClass) {
        this.driverClass = driverClass;
        return this;
    }

    public MysqlGenerator setUrl(String url) {
        this.url = url;
        return this;
    }

    public MysqlGenerator setUsername(String username) {
        this.username = username;
        return this;
    }

    public MysqlGenerator setPassword(String password) {
        this.password = password;
        return this;
    }


    private DataSourceConfig initDsc() {
        DataSourceConfig dsc = new DataSourceConfig()
                .setDriverName(driverClass)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password);
        if (null == dsc) {
            throw new RuntimeException("请配置数据源");
        }
//        dsc.setTypeConvert(new MySqlTypeConvert() {
//            @Override
//            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
//                /**
//                 * 数据库里的datetime,timestamp转成Date,也可以在GlobeConfig里面配置DateType
//                 */
//                if (fieldType.toLowerCase().contains("datetime") || fieldType.toLowerCase().contains("timestamp")) {
//                    return DbColumnType.DATE;
//                }
//                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
//            }
//        });
        return dsc;
    }

    private GlobalConfig initGc(String moduleName) {
        // 全局配置
        return new GlobalConfig().setOutputDir(projectPath + "/src/main/java")
                .setAuthor("Luter")
                //覆盖已有文件
                .setFileOverride(true)
                //使用Date而不是LocalDateTime
//                .setDateType(DateType.SQL_PACK)
                .setOpen(false)
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setEntityName(StringUtils.capitalize(moduleName) + "%sEntity")
                .setMapperName(StringUtils.capitalize(moduleName) + "%sMapper")
                .setControllerName(StringUtils.capitalize(moduleName) + "%sController")
                .setServiceName(StringUtils.capitalize(moduleName) + "%sService")
                .setServiceImplName(StringUtils.capitalize(moduleName) + "%sServiceImpl")
                //.setControllerName("%sController");
                //实体属性 Swagger2 注解
                .setSwagger2(true);
    }

    private StrategyConfig initSc(String... tables) {
        return new StrategyConfig().setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //设置基础entity
                .setSuperEntityClass(MybatisAbstractEntity.class)
                //设置基础mapper
//        strategy.setSuperMapperClass("com.luter.base.mapper.SuperMapper")
                //是否开启lombok
                .setEntityLombokModel(true)
                //加@ablefieldT注解，做数据库字段与属性映射
                .setEntityTableFieldAnnotationEnable(true)
                //产生的controller是不是带@restcontroller注解的
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)
                .setSuperControllerClass(AbstractMybatisController.class)
                .setSuperServiceClass(BaseMybatisService.class)
                .setSuperServiceImplClass(BaseMybatisServiceImpl.class)
                .setEntitySerialVersionUID(true)
                .setInclude(tables)
                //超类的字段将会被忽略
                .setSuperEntityColumns("created_time", "created_by",
                        "last_modified_by", "last_modified_time", "id", "remarks", "version")
                .setControllerMappingHyphenStyle(false)
                //boolean类型的移除is前缀
                .setEntityBooleanColumnRemoveIsPrefix(true)
                //表的前缀
                .setTablePrefix("clt_", "m_", "c_", "pet_", "demo_");
    }

    private InjectionConfig initIc(PackageConfig pc, String classDesc) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(1);
                map.put("classDesc", classDesc);
                this.setMap(map);
            }
        };

        // 如果模板引擎是 freemarker
        String mapperTemplatePath = "/templates/mapper.xml.ftl";
        String dtoTemplatePath = "/code_tpl/dto.java.ftl";
        String voTemplatePath = "/code_tpl/vo.java.ftl";
        String pojoMapperTemplatePath = "/code_tpl/pojoMapper.java.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出,自定义mapper输出
        focList.add(new FileOutConfig(mapperTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {

                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        //产生DTO
        focList.add(new FileOutConfig(dtoTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String className = tableInfo.getEntityName().replace("Entity", "DTO");
                return projectPath + "/src/main/java/" +
                        destPackage.replace(".", "/") + "/" + pc.getModuleName()
                        + "/dto/" + className
                        + StringPool.DOT_JAVA;
            }
        });
        //产生VO
        focList.add(new FileOutConfig(voTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String className = tableInfo.getEntityName().replace("Entity", "VO");
                return projectPath + "/src/main/java/" +
                        destPackage.replace(".", "/") + "/" + pc.getModuleName()
                        + "/vo/" + className
                        + StringPool.DOT_JAVA;
            }
        });
        //产生POJO Mapper
        focList.add(new FileOutConfig(pojoMapperTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String className = tableInfo.getEntityName().replace("Entity", "PojoMapper");
                return projectPath + "/src/main/java/" +
                        destPackage.replace(".", "/") + "/" + pc.getModuleName()
                        + "/pmapper/" + className
                        + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    public void gen(String moduleName, String classDesc, String... tables) {
        //数据源
        DataSourceConfig dsc = initDsc();
        //全局配置
        GlobalConfig gc = initGc(moduleName);
        // 包配置
        PackageConfig pc = new PackageConfig();
        //模块名字，产生在一个包下
        pc.setModuleName(moduleName)
                //基础包路径，
                .setParent(destPackage);
        //变量注入
        InjectionConfig cfg = initIc(pc, classDesc);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null)
                .setController("/code_tpl/ordController.java")
//                .setController("/code_tpl/controller.java")
                .setEntity("/code_tpl/entity.java")
                .setService("/code_tpl/service.java")
                .setServiceImpl("/code_tpl/serviceImpl.java")
                .setMapper("/code_tpl/mapper.java");
        // 策略配置
        StrategyConfig strategy = initSc(tables);
        //代码生成
        AutoGenerator mpg = new AutoGenerator();
        mpg.setPackageInfo(pc)
                .setGlobalConfig(gc)
                .setDataSource(dsc)
                .setCfg(cfg).setTemplate(templateConfig)
                .setStrategy(strategy)
                //使用freemarker引擎
                .setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
