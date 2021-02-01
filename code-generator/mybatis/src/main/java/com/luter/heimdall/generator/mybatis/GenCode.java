
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
        String[] tables = scanner("数据库表名称").split(",");
        String classDesc = scanner("类描述，中文，用来在api文档显示");
        new MysqlGenerator()
                .setProjectPath("目标项目的绝对路径")
                .setDestPackage("目标项目的包路径，代码会产生在这个包下面")
                .setDriverClass(driver)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password)
                .gen(moduleName, classDesc, tables);

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
