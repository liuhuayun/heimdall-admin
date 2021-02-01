package com.luter.heimdall.admin.module.sys.vo;

import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 系统角色 VO对象
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "系统角色VO对象", description = "系统角色VO对象")
@EqualsAndHashCode(callSuper = true)
public class SysRoleVO extends AbstractVO implements Serializable {

    @ApiModelProperty("")
    private String description;

    @ApiModelProperty("")
    private String name;

    private Boolean reserved;

    @ApiModelProperty("")
    private String title;
    private List<SysResourceVO> resources;
}
