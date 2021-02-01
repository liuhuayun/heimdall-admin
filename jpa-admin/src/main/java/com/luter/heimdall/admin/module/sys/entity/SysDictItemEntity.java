package com.luter.heimdall.admin.module.sys.entity;

    import com.luter.heimdall.starter.jpa.base.entity.JpaAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import javax.persistence.*;
import java.io.Serializable;
/**
* 字典项 实体类
*/
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "m_dict_item")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
    public class SysDictItemEntity extends JpaAbstractEntity implements Serializable{

        @Column(name = "item_value")
        private String itemValue ;

        @Column(name = "label")
        private String label ;

        @Column(name = "seq_no")
        private Integer seqNo ;

        @Column(name = "type_id")
        private Long typeId ;

}
