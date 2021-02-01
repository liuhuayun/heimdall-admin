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
 * 系统用户 DTO对象
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "系统用户DTO对象", description = "系统用户DTO")
@EqualsAndHashCode(callSuper = true)
public class SysUserDTO extends AbstractDTO implements Serializable {

    @ApiModelProperty("")
    private String address;

    @ApiModelProperty("")
    private String avatar;

    @ApiModelProperty("")
    private String cellPhone;

    @ApiModelProperty("")
    private String gender;

    private Boolean locked;

    @ApiModelProperty("")
    private String nickName;

    @ApiModelProperty("")
    private String password;

    @ApiModelProperty("")
    private String realName;

    @ApiModelProperty("")
    private String telPhone;

    @ApiModelProperty("")
    private String username;

    @ApiModelProperty("")
    private Long departmentId;

    List<SysRoleDTO> roles;
    List<SysPostDTO> posts;
    SysDepartmentDTO department;


}
