package com.luter.heimdall.admin.module.sys.entity;

import com.luter.heimdall.starter.boot.validator.utils.RegexConstants;
import com.luter.heimdall.starter.jpa.base.entity.JpaAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "sys_dict_type")
@org.hibernate.annotations.Table(comment = "系统字典分类表", appliesTo = "sys_dict_type")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
public class SysDictTypeEntity extends JpaAbstractEntity implements Serializable {
    @Column(name = "name", unique = true, nullable = false, length = 32)
    @Pattern(regexp = RegexConstants.CHAR, message = "字典名称仅支持大小写字母")
    private String name;
}
