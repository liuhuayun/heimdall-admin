package com.luter.macaw.jpa.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TableModel {
    private String tName;
    private String cName;
    private String remarks;
    private String cType;

}
