package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 系统用户 Repository
 */
public interface SysUserRepository extends JpaRepository<SysUserEntity, Long>, JpaSpecificationExecutor<SysUserEntity> {
    SysUserEntity findSysUserEntityByUsername(String username);
}
