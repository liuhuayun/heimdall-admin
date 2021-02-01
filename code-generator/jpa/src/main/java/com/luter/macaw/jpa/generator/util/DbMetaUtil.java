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

package com.luter.macaw.jpa.generator.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.luter.macaw.jpa.generator.config.TableModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbMetaUtil {

    public static List<TableModel> getTableInfo(String[] baseColumns, String driver, String url, String user, String pwd, String table) {
        List<TableModel> result = new ArrayList<>();

        Connection conn = null;
        DatabaseMetaData dbmd;

        try {
            conn = getConnections(driver, url, user, pwd);

            dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", table, new String[]{"TABLE"});

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(tableName);

                if (tableName.equals(table)) {
                    ResultSet rs = conn.getMetaData().getColumns(null, getSchema(conn), tableName.toUpperCase(), "%");

                    while (rs.next()) {
                        System.out.println("字段名：" + rs.getString("COLUMN_NAME") + "--字段注释：" + rs.getString("REMARKS") + "--字段数据类型：" + rs.getString("TYPE_NAME"));
                        String columnName = rs.getString("COLUMN_NAME");
                        String columnNameCamel = StrUtil.toCamelCase(columnName);
                        String remark = rs.getString("REMARKS");
                        String type = rs.getString("TYPE_NAME");
                        TableModel a = new TableModel();
                        a.setTName(columnName);
                        a.setCName(columnNameCamel);
                        a.setRemarks(remark);
                        a.setCType(type);
                        //排除公共类字段
                        if (!ArrayUtil.contains(baseColumns, columnName)) {
                            result.add(a);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private static String changeDbType(String dbType) {
        dbType = dbType.toUpperCase();
        switch (dbType) {
            case "VARCHAR":
            case "VARCHAR2":
            case "CHAR":
                return "1";
            case "NUMBER":
            case "DECIMAL":
                return "4";
            case "INT":
            case "SMALLINT":
            case "INTEGER":
                return "2";
            case "BIGINT":
                return "6";
            case "DATETIME":
            case "TIMESTAMP":
            case "DATE":
                return "7";
            default:
                return "1";
        }
    }

    //获取连接
    private static Connection getConnections(String driver, String url, String username, String password) throws Exception {
        Connection conn;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return conn;
    }

    private static String getSchema(Connection conn) throws Exception {
        String schema;
        schema = conn.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase();

    }

    public static String getDbNameByUrl(String jdbcUrl) {
        String database = null;
        int pos, pos1;
        String connUri;

        if (StrUtil.isBlank(jdbcUrl)) {
            throw new IllegalArgumentException("Invalid JDBC url.");
        }

        jdbcUrl = jdbcUrl.toLowerCase();

        if (jdbcUrl.startsWith("jdbc:impala")) {
            jdbcUrl = jdbcUrl.replace(":impala", "");
        }

        if (!jdbcUrl.startsWith("jdbc:")
                || (pos1 = jdbcUrl.indexOf(':', 5)) == -1) {
            throw new IllegalArgumentException("Invalid JDBC url.");
        }

        connUri = jdbcUrl.substring(pos1 + 1);

        if (connUri.startsWith("//")) {
            if ((pos = connUri.indexOf('/', 2)) != -1) {
                database = connUri.substring(pos + 1);
            }
        } else {
            database = connUri;
        }
        if (StrUtil.isBlank(database)) {
            throw new IllegalArgumentException("Invalid JDBC url.");
        }
        if (database.contains("?")) {
            database = database.substring(0, database.indexOf("?"));
        }

        if (database.contains(";")) {
            database = database.substring(0, database.indexOf(";"));
        }


        return database;
    }
}
