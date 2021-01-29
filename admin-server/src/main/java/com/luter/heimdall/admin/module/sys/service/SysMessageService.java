package com.luter.heimdall.admin.module.sys.service;

import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysMessageDTO;
import com.luter.heimdall.admin.module.sys.vo.SysMessageVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

public interface SysMessageService extends BaseService {


    PageDTO<SysMessageDTO> list(SysMessageVO param, PagerVO pagerVO);

    SysMessageDTO getById(Long id);

    SysMessageDTO save(SysMessageVO param);

    void update(SysMessageVO param);

    int deleteById(Long id);


}
