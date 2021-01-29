package com.luter.heimdall.admin.module.sys.dto;

import com.luter.heimdall.starter.model.base.AbstractDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value = "字典条目DTO对象", description = "字典条目DTO")
@EqualsAndHashCode(callSuper = true)
public class SysDictItemDTO extends AbstractDTO implements Serializable {
    private Long typeId;
    private String label;

    private String itemValue;
    private Integer seqNo = 0;
}
