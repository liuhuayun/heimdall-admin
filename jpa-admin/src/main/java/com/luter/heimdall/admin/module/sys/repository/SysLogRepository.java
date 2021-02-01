package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
* 系统日志 Repository
*/
public interface SysLogRepository extends JpaRepository<SysLogEntity, Long>, JpaSpecificationExecutor<SysLogEntity> {
    }
