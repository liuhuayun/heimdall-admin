package com.luter.heimdall.admin.module.sys.vo;

import com.luter.heimdall.admin.module.sys.dto.SysDepartmentDTO;
import com.luter.heimdall.admin.module.sys.dto.SysPostDTO;
import com.luter.heimdall.admin.module.sys.dto.SysRoleDTO;
import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 系统用户 VO对象
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "系统用户VO对象", description = "系统用户VO对象")
@EqualsAndHashCode(callSuper = true)
public class SysUserVO extends AbstractVO implements Serializable {

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
    //////登录需要
    private String uuid;
    private String captcha;
    /////////修改密码
//    原密码
    private String orgPassword;
    //    新密码第一次输入
    private String newPassword;
    //新密码确认输入
    private String repeatPassword;

    List<SysRoleDTO> roles;
    List<SysPostDTO> posts;
    SysDepartmentDTO department;
}
