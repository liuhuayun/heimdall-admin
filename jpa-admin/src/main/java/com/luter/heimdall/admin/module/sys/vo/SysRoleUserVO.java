package com.luter.heimdall.admin.module.sys.vo;

import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色用户关系 VO对象
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "角色用户关系VO对象", description = "角色用户关系VO对象")
@EqualsAndHashCode(callSuper = true)
public class SysRoleUserVO extends AbstractVO implements Serializable {

    @ApiModelProperty("")
    private Long userId;

    @ApiModelProperty("")
    private Long roleId;

}
