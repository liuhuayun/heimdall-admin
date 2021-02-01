package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysRoleResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
* 角色资源关系 Repository
*/
public interface SysRoleResourceRepository extends JpaRepository<SysRoleResourceEntity, Long>, JpaSpecificationExecutor<SysRoleResourceEntity> {
    }
