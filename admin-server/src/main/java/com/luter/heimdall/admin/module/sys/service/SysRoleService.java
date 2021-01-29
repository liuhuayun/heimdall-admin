package com.luter.heimdall.admin.module.sys.service;


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysRoleDTO;
import com.luter.heimdall.admin.module.sys.vo.SysRoleVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.util.List;

public interface SysRoleService extends BaseService {


    PageDTO<SysRoleDTO> list(SysRoleVO param, PagerVO pagerVO);

    List<SysRoleDTO> listAll();

    SysRoleDTO getById(Long id);

    SysRoleDTO save(SysRoleVO param);

    void update(SysRoleVO param);

    int deleteById(Long id);

    void saveRoleAuth(SysRoleVO prams);
}
