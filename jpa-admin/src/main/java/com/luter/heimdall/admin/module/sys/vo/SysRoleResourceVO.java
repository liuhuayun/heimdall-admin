package com.luter.heimdall.admin.module.sys.vo;

import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色资源关系 VO对象
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "角色资源关系VO对象", description = "角色资源关系VO对象")
@EqualsAndHashCode(callSuper = true)
public class SysRoleResourceVO extends AbstractVO implements Serializable {

    @ApiModelProperty("")
    private Long roleId;

    @ApiModelProperty("")
    private Long resourceId;

}
