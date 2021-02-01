package com.luter.heimdall.admin.module.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色用户关系 实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "m_role_user")
@Data
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
@IdClass(SysRoleUserIds.class)
public class SysRoleUserEntity implements Serializable {

    @Column(name = "user_id")
    @Id
    private Long userId;
    @Id
    @Column(name = "role_id")
    private Long roleId;

}
