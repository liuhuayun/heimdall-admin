package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysPostUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
* 岗位用户关系 Repository
*/
public interface SysPostUserRepository extends JpaRepository<SysPostUserEntity, Long>, JpaSpecificationExecutor<SysPostUserEntity> {
    }
