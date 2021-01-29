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
@ApiModel(value = "组织机构VO对象", description = "组织机构VO对象")
@EqualsAndHashCode(callSuper = true)
public class SysDepartmentVO extends AbstractVO implements Serializable {

    private Boolean enabled;

    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("")
    private Long pid;

    @ApiModelProperty("")
    private Integer seqNo;
    @ApiModelProperty("是否执行搜索")
    private Boolean searched = false;

}
