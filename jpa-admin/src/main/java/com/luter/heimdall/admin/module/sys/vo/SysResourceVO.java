package com.luter.heimdall.admin.module.sys.vo;

import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统资源 VO对象
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "系统资源VO对象", description = "系统资源VO对象")
@EqualsAndHashCode(callSuper = true)
public class SysResourceVO extends AbstractVO implements Serializable {

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
    @ApiModelProperty("是否执行搜索")
    private Boolean searched = false;

}
