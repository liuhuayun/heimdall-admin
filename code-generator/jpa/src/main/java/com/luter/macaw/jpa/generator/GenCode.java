
package com.luter.macaw.jpa.generator;


import cn.hutool.core.util.StrUtil;

import java.util.Scanner;

public class GenCode {
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://1.1.1.1:1234/heimdall_admin_db?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai";
    static String username = "foo";
    static String password = "bar";

    public static void main(String[] args) {
        genMysqlCode();
    }

    public static void genMysqlCode() {
        String moduleName = scanner("模块名，顶层包的名字，英文小写");
        String[] tables = scanner("表名").split(",");
        String[] classDesc = scanner("类描述").split(",");

        new MysqlGenerator()
                .setDriverClass(driver)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password)
                .setBaseColumns(new String[]{"id", "created_by", "created_time", "last_modified_by", "last_modified_time", "remarks", "version"})
                .setDestBasePath("代码生成在这个目录下，绝对路径")
                .setDestBasePkgPath("java 包路径")
                .setModuleName(moduleName)
                .setTables(tables)
                .setBaseController("com.luter.macaw.starter.jpa.base.controller.BaseJpaController")
                .setBaseDTO("com.luter.macaw.starter.model.base.AbstractDTO")
                .setBaseVO("com.luter.macaw.starter.model.base.AbstractVO")
                .setBaseEntity("com.luter.macaw.starter.jpa.base.entity.JpaAbstractEntity")
                .setBaseService("com.luter.macaw.petstore.base.service.BaseService")
                .setBaseServiceImpl("com.luter.macaw.petstore.base.service.impl.BaseServiceImpl")
                .setModuleDesc(classDesc)
                .setCType(new String[]{"C", "D", "E", "V", "R", "S", "M"})
                .genTablesCode();


    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StrUtil.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new RuntimeException("参数输入错误!");
    }

}
