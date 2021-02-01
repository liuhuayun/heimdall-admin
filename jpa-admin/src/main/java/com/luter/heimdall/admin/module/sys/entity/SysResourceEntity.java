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
* 系统资源 实体类
*/
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "m_resource")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
    public class SysResourceEntity extends JpaAbstractEntity implements Serializable{

        @Column(name = "is_enabled")
        private Boolean enabled ;

        @Column(name = "icon")
        private String icon ;

        @Column(name = "method")
        private String method ;

        @Column(name = "name")
        private String name ;

        @Column(name = "perm")
        private String perm ;

        @Column(name = "pid")
        private Long pid ;

        @Column(name = "res_type")
        private Integer resType ;

        @Column(name = "title")
        private String title ;

        @Column(name = "uri")
        private String uri ;

        @Column(name = "is_affix")
        private Boolean affix ;

        @Column(name = "is_keep_alive")
        private Boolean keepAlive ;

        @Column(name = "target")
        private String target ;

        @Column(name = "component")
        private String component ;

        @Column(name = "is_hidden")
        private Boolean hidden ;

        @Column(name = "path")
        private String path ;

        @Column(name = "seq_no")
        private Integer seqNo ;

}
