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
* 岗位职责 实体类
*/
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "m_post")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
    public class SysPostEntity extends JpaAbstractEntity implements Serializable{

        @Column(name = "name")
        private String name ;

        @Column(name = "code")
        private String code ;

        @Column(name = "enabled")
        private Boolean enabled ;

        @Column(name = "seq_no")
        private Integer seqNo ;

}
