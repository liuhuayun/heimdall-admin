/*
 *
 *  *
 *  *
 *  *      Copyright 2020-2021 Luter.me
 *  *
 *  *      Licensed under the Apache License, Version 2.0 (the "License");
 *  *      you may not use this file except in compliance with the License.
 *  *      You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *      Unless required by applicable law or agreed to in writing, software
 *  *      distributed under the License is distributed on an "AS IS" BASIS,
 *  *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *      See the License for the specific language governing permissions and
 *  *      limitations under the License.
 *  *
 *  *
 *
 */

package com.luter.heimdall.admin.module.sys.entity;

import com.luter.heimdall.starter.jpa.base.entity.JpaAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sys_log")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Table(comment = "系统日志表", appliesTo = "sys_log")
@Data
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
public class SysLogEntity extends JpaAbstractEntity {
    private static final long serialVersionUID = 454843424836693660L;
    @Column(name = "title")
    private String title;

    @Column(name = "business_type")
    private Integer businessType;

    @Column(name = "method")
    private String method;

    @Column(name = "request_method")
    private String requestMethod;

    @Column(name = "terminal_type")
    private Integer terminalType;
    @Column(name = "terminal_os_name")
    private String terminalOsName;
    @Column(name = "browser_name")
    private String browserName;
    @Column(name = "browser_version")
    private String browserVersion;
    @Column(name = "username")
    private String username;

    @Column(name = "request_url")
    private String requestUrl;

    @Column(name = "request_ip")
    private String requestIp;
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_location")
    private String userLocation;

    @Column(name = "request_param")
    private String requestParam;


    @Column(name = "status")
    private Integer status;

    @Lob
    @Column(name = "exception_message")
    private String exceptionMessage;

    @Lob
    @Column(name = "response_result")
    private String responseResult;
    @Column(name = "request_time")
    private LocalDateTime requestTime;
    @Column(name = "app_name")
    private String appName;
    @Column(name = "app_host_ip")
    private String appHostIp;
    @Column(name = "app_port")
    private String appPort;

    @Column(name = "consuming_time")
    private Long consumingTime;
}
