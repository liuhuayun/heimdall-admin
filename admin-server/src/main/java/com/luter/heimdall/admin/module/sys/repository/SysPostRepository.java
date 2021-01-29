package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface SysPostRepository extends JpaRepository<SysPostEntity, Long>, JpaSpecificationExecutor<SysPostEntity> {
    }
