package com.luter.heimdall.admin.module.sys.vo;
    import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;
/**
* 字典项 VO对象
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "字典项VO对象",description = "字典项VO对象")
@EqualsAndHashCode(callSuper = true)
    public class SysDictItemVO extends AbstractVO implements Serializable{

        @ApiModelProperty("")
        private String itemValue ;

        @ApiModelProperty("")
        private String label ;

        @ApiModelProperty("")
        private Integer seqNo ;

        @ApiModelProperty("")
        private Long typeId ;

}
