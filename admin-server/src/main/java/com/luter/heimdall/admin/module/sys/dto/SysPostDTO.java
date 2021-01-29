package com.luter.heimdall.admin.module.sys.dto;

import com.luter.heimdall.starter.model.base.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value = "岗位DTO对象", description = "岗位DTO")
@EqualsAndHashCode(callSuper = true)
public class SysPostDTO extends AbstractDTO implements Serializable {

    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("")
    private String code;

    private Boolean enabled;

    @ApiModelProperty("")
    private Integer seqNo;

}
