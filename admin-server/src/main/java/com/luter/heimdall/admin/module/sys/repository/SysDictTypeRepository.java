package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysDictTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface SysDictTypeRepository extends JpaRepository<SysDictTypeEntity, Long>, JpaSpecificationExecutor<SysDictTypeEntity> {
    }
