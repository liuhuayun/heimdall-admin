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

    @ApiModelProperty("电话")
    private String telphone;

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("岗位")
    private List<SysPostDTO> posts;

    @ApiModelProperty("组织机构")
    private SysDepartmentDTO department;

    @JsonIgnore
    private List<SysRoleDTO> roles;


}
