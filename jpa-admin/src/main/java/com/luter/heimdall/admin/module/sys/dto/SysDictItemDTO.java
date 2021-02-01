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
* 字典项 DTO对象
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "字典项DTO对象",description = "字典项DTO")
@EqualsAndHashCode(callSuper = true)
    public class SysDictItemDTO extends AbstractDTO implements Serializable{

        @ApiModelProperty("")
        private String itemValue ;

        @ApiModelProperty("")
        private String label ;

        @ApiModelProperty("")
        private Integer seqNo ;

        @ApiModelProperty("")
        private Long typeId ;

}
