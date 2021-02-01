package com.luter.heimdall.admin.module.sys.entity;

import com.luter.heimdall.starter.jpa.base.entity.JpaAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统消息 实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "m_message")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
public class SysMessageEntity extends JpaAbstractEntity implements Serializable {

    @Column(name = "content")
    private String content;

    @Column(name = "msg_type")
    private Integer msgType;

    @Column(name = "send_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "title")
    private String title;

}
