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
* 系统角色 实体类
*/
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "m_role")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
    public class SysRoleEntity extends JpaAbstractEntity implements Serializable{

        @Column(name = "description")
        private String description ;

        @Column(name = "name")
        private String name ;

        @Column(name = "is_reserved")
        private Boolean reserved ;

        @Column(name = "title")
        private String title ;

}
