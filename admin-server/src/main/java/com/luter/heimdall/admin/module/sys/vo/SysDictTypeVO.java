package com.luter.heimdall.admin.module.sys.vo;

import com.luter.heimdall.starter.boot.validator.utils.RegexConstants;
import com.luter.heimdall.starter.model.base.AbstractVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value = "字典分类VO对象", description = "字典分类VO对象")
@EqualsAndHashCode(callSuper = true)
public class SysDictTypeVO extends AbstractVO implements Serializable {
    @NotBlank(message = "字典名称不能为空")
    @Pattern(regexp = RegexConstants.CHAR, message = "只能输入大小写字母")
    private String name;
}
