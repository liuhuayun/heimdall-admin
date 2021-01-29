package com.luter.heimdall.admin.module.sys.service;


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysMessageBoxDTO;
import com.luter.heimdall.admin.module.sys.vo.SysMessageBoxVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

public interface SysMessageBoxService extends BaseService {


    PageDTO<SysMessageBoxDTO> list(SysMessageBoxVO param, PagerVO pagerVO);

    SysMessageBoxDTO getById(Long id);

    SysMessageBoxDTO save(SysMessageBoxVO param);

    void update(SysMessageBoxVO param);

    int deleteById(Long id);


}
