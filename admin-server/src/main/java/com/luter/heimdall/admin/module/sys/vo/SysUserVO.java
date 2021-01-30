/*
 *
 *  *
 *  *
 *  *      Copyright 2020-2021 Luter.me
 *  *
 *  *      Licensed under the Apache License, Version 2.0 (the "License");
 *  *      you may not use this file except in compliance with the License.
 *  *      You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *      Unless required by applicable law or agreed to in writing, software
 *  *      distributed under the License is distributed on an "AS IS" BASIS,
 *  *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *      See the License for the specific language governing permissions and
 *  *      limitations under the License.
 *  *
 *  *
 *  
 */

package com.luter.heimdall.admin.module.sys.vo;

import com.luter.heimdall.starter.boot.validator.utils.RegexConstants;
import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

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
    private String realName;

    @ApiModelProperty("电话")
    private String telphone;
    @ApiModelProperty("")
    @Pattern(regexp = RegexConstants.PASSWORD_SIMPLE, message = "密码长度应该在6~18之间，包含字符、数字和下划线")
    private String password;
    @ApiModelProperty("用户名")
    @Pattern(regexp = RegexConstants.USERNAME, message = "用户名由字母数字下划线组成且开头必须是字母，不超过16位")
    private String username;
    @ApiModelProperty("")
    private List<SysRoleVO> userRoles;
    @ApiModelProperty("")
    private List<SysPostVO> posts;
    @ApiModelProperty("组织机构")
    private SysDepartmentVO department;


    ////////////实体类没有的字段
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
}
