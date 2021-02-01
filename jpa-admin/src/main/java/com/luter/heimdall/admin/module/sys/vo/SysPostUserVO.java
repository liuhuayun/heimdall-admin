package com.luter.heimdall.admin.module.sys.vo;
    import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
/**
* 岗位用户关系 VO对象
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "岗位用户关系VO对象",description = "岗位用户关系VO对象")
@EqualsAndHashCode(callSuper = true)
    public class SysPostUserVO extends AbstractVO implements Serializable{

        @ApiModelProperty("")
        private Long userId ;

        @ApiModelProperty("")
        private Long postId ;

}
