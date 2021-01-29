package com.luter.heimdall.admin.module.sys.service;


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysDictItemDTO;
import com.luter.heimdall.admin.module.sys.vo.SysDictItemVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.util.List;

public interface SysDictItemService extends BaseService {


    PageDTO<SysDictItemDTO> list(SysDictItemVO param, PagerVO pagerVO);

    List<SysDictItemDTO> listByTypeId(Long typeId);

    List<SysDictItemDTO> listByTypeName(String typeName);

    SysDictItemDTO getById(Long id);

    SysDictItemDTO save(SysDictItemVO param);

    void update(SysDictItemVO param);

    int deleteById(Long id);


}
