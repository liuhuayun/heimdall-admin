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
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@ApiModel(value = "系统日志DTO对象", description = "系统日志DTO")
@EqualsAndHashCode(callSuper = true)
public class SysLogDTO extends AbstractDTO implements Serializable {

    @ApiModelProperty("")
    private String appHostIp;


    @ApiModelProperty("")
    private String appName;


    @ApiModelProperty("")
    private String appPort;


    @ApiModelProperty("")
    private String browserName;


    @ApiModelProperty("")
    private String browserVersion;


    @ApiModelProperty("")
    private Integer businessType;


    @ApiModelProperty("")
    private Long consumingTime;


    @ApiModelProperty("")
    private String exceptionMessage;


    @ApiModelProperty("")
    private String method;


    @ApiModelProperty("")
    private String requestIp;


    @ApiModelProperty("")
    private String requestMethod;


    @ApiModelProperty("")
    private String requestParam;


    @ApiModelProperty("")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestTime;


    @ApiModelProperty("")
    private String requestUrl;


    @ApiModelProperty("")
    private String responseResult;


    @ApiModelProperty("")
    private Integer status;


    @ApiModelProperty("")
    private String terminalOsName;


    @ApiModelProperty("")
    private Integer terminalType;


    @ApiModelProperty("")
    private String title;


    @ApiModelProperty("")
    private String userId;


    @ApiModelProperty("")
    private String userLocation;


    @ApiModelProperty("")
    private String username;

}
