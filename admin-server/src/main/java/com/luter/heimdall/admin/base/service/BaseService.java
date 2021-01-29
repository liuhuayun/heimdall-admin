package com.luter.heimdall.admin.base.service;


import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.admin.module.sys.entity.SysUserEntity;
import com.luter.heimdall.starter.jpa.base.service.BaseJpaService;

public interface BaseService extends BaseJpaService {


    SysUserDTO getCurrentUserDTO(boolean isThrowEx, boolean fromDb);

    SysUserEntity getCurrentUserEntity(boolean isThrowEx);

}
