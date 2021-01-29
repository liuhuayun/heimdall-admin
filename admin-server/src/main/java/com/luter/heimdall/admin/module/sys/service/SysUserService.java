package com.luter.heimdall.admin.module.sys.service;


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.admin.module.sys.entity.SysUserEntity;
import com.luter.heimdall.admin.module.sys.vo.SysUserVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.io.Serializable;

public interface SysUserService extends BaseService {


    PageDTO<SysUserDTO> list(SysUserVO param, PagerVO pagerVO);

    SysUserDTO getById(Long id);


    SysUserDTO save(SysUserVO param);

    void update(SysUserVO param);

    void updateUserInfo(SysUserEntity user);

    int deleteById(Long id);

    void  resetPassword(SysUserVO param);

    Serializable login(SysUserVO user);

    void logout();


}
