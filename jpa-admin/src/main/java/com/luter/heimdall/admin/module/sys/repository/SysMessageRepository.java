package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
* 系统消息 Repository
*/
public interface SysMessageRepository extends JpaRepository<SysMessageEntity, Long>, JpaSpecificationExecutor<SysMessageEntity> {
    }
