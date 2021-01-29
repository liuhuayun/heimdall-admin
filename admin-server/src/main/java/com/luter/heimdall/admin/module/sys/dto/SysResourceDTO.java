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
@ApiModel(value = "系统资源DTO对象", description = "系统资源DTO")
@EqualsAndHashCode(callSuper = true)
public class SysResourceDTO extends AbstractDTO implements Serializable {


    private Boolean enabled;


    @ApiModelProperty("")
    private String icon;

    @ApiModelProperty("")
    private String method;


    @ApiModelProperty("")
    private String name;


    @ApiModelProperty("")
    private String perm;


    @ApiModelProperty("")
    private Long pid;


    @ApiModelProperty("")
    private Integer resType;

    @ApiModelProperty("")
    private Integer seqNo;

    @ApiModelProperty("")
    private String title;


    @ApiModelProperty("")
    private String uri;


    private Boolean affix;



    @ApiModelProperty("")
    private String target;

    private Boolean keepAlive;


    @ApiModelProperty("")
    private String component;


    private Boolean hidden;


    @ApiModelProperty("")
    private String path;
    @ApiModelProperty("")
    List<SysResourceDTO> children;

}
