package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysMessageBoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
* 系统信箱 Repository
*/
public interface SysMessageBoxRepository extends JpaRepository<SysMessageBoxEntity, Long>, JpaSpecificationExecutor<SysMessageBoxEntity> {
    }
