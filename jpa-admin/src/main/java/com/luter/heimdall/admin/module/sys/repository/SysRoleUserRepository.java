package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysRoleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
* 角色用户关系 Repository
*/
public interface SysRoleUserRepository extends JpaRepository<SysRoleUserEntity, Long>, JpaSpecificationExecutor<SysRoleUserEntity> {
    }
