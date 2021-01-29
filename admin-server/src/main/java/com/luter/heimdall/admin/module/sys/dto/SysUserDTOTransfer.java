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

package com.luter.heimdall.admin.module.sys.dto;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luter.heimdall.starter.model.base.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Data
@Accessors(chain = true)
@ApiModel(value = "系统用户DTO 前端对象", description = "系统用户DTO前端数据对象")
@EqualsAndHashCode(callSuper = true)
public class SysUserDTOTransfer extends AbstractDTO implements Serializable {

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

    public List<Long> getRoleIds() {
        if (null != this.roles && !this.roles.isEmpty()) {
            return roles.stream().map(SysRoleDTO::getId).collect(Collectors.toList());
        }
        return null;
    }

    public List<String> getRoleNames() {
        if (null != this.roles && !this.roles.isEmpty()) {
            return roles.stream().map(SysRoleDTO::getName).collect(Collectors.toList());
        }
        return null;
    }

    public List<String> getPerms() {
        List<SysResourceDTO> resources = this.getResources();
        if (null != resources && !resources.isEmpty()) {
            return resources.stream().filter(
                    d -> 3 == d.getResType() && StrUtil.isNotEmpty(d.getPerm()))
                    .map(SysResourceDTO::getPerm)
                    .collect(Collectors.toList());
        }

        return null;
    }

    @JsonIgnore
    public List<SysResourceDTO> getResources() {
        if (null != this.roles && !this.roles.isEmpty()) {
            List<SysResourceDTO> all = new ArrayList<>();
            for (SysRoleDTO role : this.roles) {
                if (null != role.getResources() && !role.getResources().isEmpty()) {
                    all.addAll(role.getResources());
                }
            }
            return all.stream().collect(collectingAndThen(toCollection(()
                    -> new TreeSet<>(comparingLong(SysResourceDTO::getId))), ArrayList::new));
        }
        return null;
    }

    @JsonIgnore
    public List<SysResourceDTO> getPermissions() {
        List<SysResourceDTO> resources = this.getResources();
        if (null != resources && !resources.isEmpty()) {
            return resources.stream().filter(
                    d -> 3 == d.getResType() && StrUtil.isNotEmpty(d.getPerm()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<SysRoleDTO> getUserRoles() {
        if (null != this.roles && !this.roles.isEmpty()) {
            List<SysRoleDTO> all = new ArrayList<>();
            for (SysRoleDTO sysRoleDTO : this.roles) {
                SysRoleDTO role = new SysRoleDTO();
                role.setName(sysRoleDTO.getName());
                role.setId(sysRoleDTO.getId());
                role.setRemarks(sysRoleDTO.getRemarks());
                all.add(role);
            }
            return all;
        }

        return null;
    }


    @JsonIgnore
    public List<SysResourceDTO> getMenuList() {
        List<SysResourceDTO> resources = this.getResources();
        if (null != resources && !resources.isEmpty()) {
            return this.getResources().stream().filter(d -> 3 != d.getResType()).collect(Collectors.toList());
        }
        return null;
    }

    @JsonIgnore
    public SysResourceDTO getMenuTree() {
        List<SysResourceDTO> resources = this.getMenuList();
        if (null != resources && !resources.isEmpty()) {
            //拷贝一个list
            List<SysResourceDTO> newOne = new ArrayList<>();
            for (SysResourceDTO resource : resources) {
                if (null != resource) {
                    SysResourceDTO a = new SysResourceDTO();
                    BeanUtils.copyProperties(resource, a);
                    newOne.add(a);
                }
            }
            SysResourceDTO root = new SysResourceDTO();
            root.setId(0L);
            root.setName("RootNode");
            root.setTitle("根节点");
            root.setEnabled(true);
            root.setHidden(true);
            //排个序
            List<SysResourceDTO> sortedList = newOne.stream().sorted(Comparator.comparing(SysResourceDTO::getSeqNo)).collect(Collectors.toList());
            //找子节点
            for (SysResourceDTO l : sortedList) {
                List<SysResourceDTO> children = sortedList.stream()
                        .filter(f -> l.getId().equals(f.getPid())).collect(Collectors.toList());
                l.setChildren(children);
            }
            //确定根节点
            return root.setChildren(sortedList.stream()
                    .filter(f -> null != f.getPid() && f.getPid().equals(root.getId())).collect(Collectors.toList()));
        }
        return null;
    }

}
