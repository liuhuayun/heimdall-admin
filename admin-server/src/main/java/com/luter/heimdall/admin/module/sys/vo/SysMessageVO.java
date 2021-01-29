package com.luter.heimdall.admin.module.sys.vo;

import com.luter.heimdall.starter.model.base.AbstractVO;
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
@ApiModel(value = "系统消息内容VO对象", description = "系统消息内容VO对象")
@EqualsAndHashCode(callSuper = true)
public class SysMessageVO extends AbstractVO implements Serializable {

    @ApiModelProperty("")
    private String content;

    @ApiModelProperty("")
    private Integer msgType;

    @ApiModelProperty("")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @ApiModelProperty("")
    private Long senderId;

    @ApiModelProperty("")
    private String title;

}
