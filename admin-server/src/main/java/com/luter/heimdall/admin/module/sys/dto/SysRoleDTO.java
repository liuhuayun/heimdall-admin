package com.luter.heimdall.admin.module.sys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luter.heimdall.starter.model.base.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Accessors(chain = true)
@ApiModel(value = "系统角色DTO对象", description = "系统角色DTO")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleDTO extends AbstractDTO implements Serializable {

    @ApiModelProperty("")
    private String description;


    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("")
    private String title;


    private Boolean reserved;
    @JsonIgnore
    private List<SysResourceDTO> resources;


}
