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

/**
 * 系统日志 DTO对象
 */
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
