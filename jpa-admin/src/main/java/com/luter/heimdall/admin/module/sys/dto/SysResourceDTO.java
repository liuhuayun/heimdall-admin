package com.luter.heimdall.admin.module.sys.dto;

import com.luter.heimdall.starter.model.base.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 系统资源 DTO对象
 */
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
    private String title;

    @ApiModelProperty("")
    private String uri;

    private Boolean affix;

    private Boolean keepAlive;

    @ApiModelProperty("")
    private String target;

    @ApiModelProperty("")
    private String component;

    private Boolean hidden;

    @ApiModelProperty("")
    private String path;

    @ApiModelProperty("")
    private Integer seqNo;

    List<SysResourceDTO> children;


}
