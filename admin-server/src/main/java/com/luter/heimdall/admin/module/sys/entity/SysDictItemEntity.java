package com.luter.heimdall.admin.module.sys.entity;

import com.luter.heimdall.starter.jpa.base.entity.JpaAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_dict_item")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Table(comment = "系统字典条目表", appliesTo = "sys_dict_item")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
public class SysDictItemEntity extends JpaAbstractEntity implements Serializable {
    @Column(name = "type_id", nullable = false)
    private Long typeId;
    @Column(name = "label", nullable = false, length = 32)
    private String label;

    @Column(name = "item_value", nullable = false, length = 32)
    private String itemValue;
    @Column(name = "seq_no")
    private Integer seqNo = 0;
}
