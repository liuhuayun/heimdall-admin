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

@Entity
@Table(name = "sys_post")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Table(comment = "系统岗位表", appliesTo = "sys_post")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
public class SysPostEntity extends JpaAbstractEntity implements Serializable {

    @Column(name = "name", unique = true, length = 32)
    @NotBlank(message = "岗位名称不能为空")
    private String name;
    @Column(name = "code", unique = true, length = 32)
    @NotBlank(message = "岗位编码不能为空")
    private String code;
    @Column(name = "seq_no")
    private Integer seqNo;

    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default false")
    private Boolean enabled;


}
