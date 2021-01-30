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
@Table(name = "sys_role")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Table(comment = "系统角色表", appliesTo = "sys_role")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
public class SysRoleEntity extends JpaAbstractEntity implements Serializable {
    @Column(name = "name", unique = true, length = 32)
    @NotBlank(message = "名称不能为空")
    private String name;
    @Column(name = "title", unique = true, length = 32)
    @NotBlank(message = "标题不能为空")
    private String title;
    private String description;
    @Column(name = "is_reserved", nullable = false, columnDefinition = "boolean default false")
    private Boolean reserved;
    @ManyToMany(targetEntity = SysResourceEntity.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_role_resource",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
    private Set<SysResourceEntity> resources;
}
