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
 * 系统信箱 实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "m_message_box")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
public class SysMessageBoxEntity extends JpaAbstractEntity implements Serializable {

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "msg_type")
    private Integer msgType;

    @Column(name = "received_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receivedTime;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "title")
    private String title;

}
