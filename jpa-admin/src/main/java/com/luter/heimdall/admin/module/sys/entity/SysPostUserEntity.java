package com.luter.heimdall.admin.module.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 岗位用户关系 实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "m_post_user")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
@IdClass(SysPostUserIds.class)
@EqualsAndHashCode
public class SysPostUserEntity implements Serializable {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "post_id")
    private Long postId;

}
