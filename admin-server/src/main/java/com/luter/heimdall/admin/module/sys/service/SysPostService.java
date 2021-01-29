package com.luter.heimdall.admin.module.sys.service;

import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysPostDTO;
import com.luter.heimdall.admin.module.sys.vo.SysPostVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

public interface SysPostService extends BaseService {


    PageDTO<SysPostDTO> list(SysPostVO param, PagerVO pagerVO);

    SysPostDTO getById(Long id);

    SysPostDTO save(SysPostVO param);

    void update(SysPostVO param);

    int deleteById(Long id);


}
