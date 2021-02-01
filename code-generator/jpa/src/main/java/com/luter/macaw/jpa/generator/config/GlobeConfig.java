package com.luter.macaw.jpa.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GlobeConfig {
    private String driverClass;
    private String url;
    private String username;
    private String password;
    private String[] baseColumns;
    private String destBasePath;
    private String destBasePkgPath;
    private String moduleName;
    private String tableName;
    private String moduleDesc;

    private String baseController;
    private String baseService;
    private String baseServiceImpl;
    private String baseDTO;
    private String baseEntity;
    List<TableModel> fields;


}
