package com.luter.heimdall.admin.module.sys.vo;

import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value = "岗位VO对象", description = "岗位VO对象")
@EqualsAndHashCode(callSuper = true)
public class SysPostVO extends AbstractVO implements Serializable {

    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("")
    private String code;

    private Boolean enabled;

    @ApiModelProperty("")
    private Integer seqNo;

}
