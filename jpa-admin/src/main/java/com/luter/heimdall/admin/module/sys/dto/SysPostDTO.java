package com.luter.heimdall.admin.module.sys.dto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
    import com.luter.heimdall.starter.model.base.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;
/**
* 岗位职责 DTO对象
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "岗位职责DTO对象",description = "岗位职责DTO")
@EqualsAndHashCode(callSuper = true)
    public class SysPostDTO extends AbstractDTO implements Serializable{

        @ApiModelProperty("")
        private String name ;

        @ApiModelProperty("")
        private String code ;

        private Boolean enabled ;

        @ApiModelProperty("")
        private Integer seqNo ;

}
