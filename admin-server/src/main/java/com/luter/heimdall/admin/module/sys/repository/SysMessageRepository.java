package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface SysMessageRepository extends JpaRepository<SysMessageEntity, Long>, JpaSpecificationExecutor<SysMessageEntity> {
    }
