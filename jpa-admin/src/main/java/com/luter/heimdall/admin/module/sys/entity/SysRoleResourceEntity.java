package com.luter.heimdall.admin.module.sys.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色资源关系 实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "m_role_resource")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
@IdClass(SysRoleResourceIds.class)
public class SysRoleResourceEntity implements Serializable {

    @Id
    @Column(name = "role_id")
    private Long roleId;
    @Id
    @Column(name = "resource_id")
    private Long resourceId;

}
