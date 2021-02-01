package com.luter.heimdall.admin.module.sys.dto;

import com.luter.heimdall.starter.model.base.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 组织机构 DTO对象
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "组织机构DTO对象", description = "组织机构DTO")
@EqualsAndHashCode(callSuper = true)
public class SysDepartmentDTO extends AbstractDTO implements Serializable {

    private Boolean enabled;

    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("")
    private Long pid;

    @ApiModelProperty("")
    private Integer seqNo;
    List<SysDepartmentDTO> children;
}
