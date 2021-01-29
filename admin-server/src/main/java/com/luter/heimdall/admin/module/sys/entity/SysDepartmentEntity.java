package com.luter.heimdall.admin.module.sys.entity;

import com.luter.heimdall.starter.jpa.base.entity.JpaAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Table(name = "sys_department")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Table(comment = "组织机构表", appliesTo = "sys_department")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = TABLE_PER_CLASS)
@Accessors(chain = true)
public class SysDepartmentEntity extends JpaAbstractEntity implements Serializable {
    @Column(name = "pid", nullable = false)
    private Long pid;
    @Column(name = "name", nullable = false, length = 32)
    @NotBlank(message = "名称不能为空")
    private String name;
    @Column(name = "seq_no")
    private Integer seqNo;
    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default false")
    private Boolean enabled;

}
