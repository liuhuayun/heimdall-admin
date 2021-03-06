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

package com.luter.heimdall.admin.module.sys.entity;

import com.luter.heimdall.starter.jpa.base.entity.JpaAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "sys_user")
@org.hibernate.annotations.Table(comment = "系统用户表", appliesTo = "sys_user")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
public class SysUserEntity extends JpaAbstractEntity implements Serializable {
    @Column(name = "username", unique = true, nullable = false, length = 30)
    @NotBlank
    private String username;
    @Column(name = "password", length = 64)
    @NotBlank
    private String password;
    private String realName;
    private String nickName;
    private String avatar;
    private String gender;

    private String cellPhone;
    private String telphone;
    private String address;
    @Column(name = "is_locked", nullable = false, columnDefinition = "boolean default false")
    private Boolean locked;
    @ManyToMany(targetEntity = SysRoleEntity.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_role_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<SysRoleEntity> roles;

    @ManyToMany(targetEntity = SysPostEntity.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_post_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")})
    private Set<SysPostEntity> posts;
    @ManyToOne
    private SysDepartmentEntity department;
}
