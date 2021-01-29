package com.luter.heimdall.admin.module.sys.service;


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysLogDTO;
import com.luter.heimdall.admin.module.sys.vo.SysLogVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.util.List;

public interface SysLogService extends BaseService {


    PageDTO<SysLogDTO> list(SysLogVO param, PagerVO pagerVO);

    SysLogDTO getById(Long id);

    SysLogDTO save(SysLogVO param);

    void update(SysLogVO param);

    int deleteById(Long id);

    int deleteByBatch(List<Long> ids);

}
