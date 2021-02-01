package com.luter.heimdall.admin.module.sys.entity;

import com.luter.heimdall.starter.jpa.base.entity.JpaAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 系统用户 实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "m_user")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
public class SysUserEntity extends JpaAbstractEntity implements Serializable {

    @Column(name = "address")
    private String address;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "cell_phone")
    private String cellPhone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "is_locked")
    private Boolean locked;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "password")
    private String password;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "tel_phone")
    private String telPhone;

    @Column(name = "username")
    private String username;

    @Column(name = "department_id")
    private Long departmentId;

}
