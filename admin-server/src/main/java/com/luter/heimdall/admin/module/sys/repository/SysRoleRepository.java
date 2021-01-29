package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface SysRoleRepository extends JpaRepository<SysRoleEntity, Long>, JpaSpecificationExecutor<SysRoleEntity> {
    }
