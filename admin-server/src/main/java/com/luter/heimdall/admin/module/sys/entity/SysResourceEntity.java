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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luter.heimdall.starter.jpa.base.entity.JpaAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_resource")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Table(comment = "系统资源表", appliesTo = "sys_resource")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lastModifiedTime"})
@Accessors(chain = true)
public class SysResourceEntity extends JpaAbstractEntity implements Serializable {
    @Column(nullable = false, name = "pid")
    private Long pid;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    private String title;
    private String uri;
    private String path;
    private String component;
    private String method;
    private String icon;

    @Column(name = "res_type", nullable = false)
    private Integer resType;
    @Column(name = "seq_no", nullable = false)
    private Integer seqNo;
    private String perm;
    @Column(name = "is_hidden", nullable = false, columnDefinition = "boolean default false")
    private Boolean hidden;
    @Column(name = "is_enabled", nullable = false, columnDefinition = "boolean default true")
    private Boolean enabled;
    @Column(name = "is_affix", nullable = false, columnDefinition = "boolean default false")
    private Boolean affix;
    private String target;
    @Column(name = "is_keep_alive", nullable = false, columnDefinition = "boolean default false")
    private Boolean keepAlive;
}
