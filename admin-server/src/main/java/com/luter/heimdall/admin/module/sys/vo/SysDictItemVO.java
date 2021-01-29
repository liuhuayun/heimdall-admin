package com.luter.heimdall.admin.module.sys.vo;

import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value = "字典条目VO对象", description = "字典条目VO对象")
@EqualsAndHashCode(callSuper = true)
public class SysDictItemVO extends AbstractVO implements Serializable {
    private Long typeId;
    private String label;

    private String itemValue;
    private Integer seqNo = 0;
}
