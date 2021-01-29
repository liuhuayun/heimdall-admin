package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SysResourceRepository extends JpaRepository<SysResourceEntity, Long>, JpaSpecificationExecutor<SysResourceEntity> {

    List<SysResourceEntity> findSysResourceEntitiesByResType(Integer resType);


}
