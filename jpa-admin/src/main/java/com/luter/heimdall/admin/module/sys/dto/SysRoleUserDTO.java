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
* 角色用户关系 DTO对象
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "角色用户关系DTO对象",description = "角色用户关系DTO")
@EqualsAndHashCode(callSuper = true)
    public class SysRoleUserDTO extends AbstractDTO implements Serializable{

        @ApiModelProperty("")
        private Long userId ;

        @ApiModelProperty("")
        private Long roleId ;

}
