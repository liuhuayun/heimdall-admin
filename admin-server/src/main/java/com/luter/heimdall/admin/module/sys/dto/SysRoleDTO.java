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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luter.heimdall.starter.model.base.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Accessors(chain = true)
@ApiModel(value = "系统角色DTO对象", description = "系统角色DTO")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleDTO extends AbstractDTO implements Serializable {

    @ApiModelProperty("")
    private String description;


    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("")
    private String title;


    private Boolean reserved;
    @JsonIgnore
    private List<SysResourceDTO> resources;


}
