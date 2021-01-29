package com.luter.heimdall.admin.module.sys.service;


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysDictTypeDTO;
import com.luter.heimdall.admin.module.sys.vo.SysDictTypeVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.util.List;

public interface SysDictTypeService extends BaseService {


    PageDTO<SysDictTypeDTO> list(SysDictTypeVO param, PagerVO pagerVO);

    List<SysDictTypeDTO> listAll();

    SysDictTypeDTO getById(Long id);

    SysDictTypeDTO save(SysDictTypeVO param);

    void update(SysDictTypeVO param);

    int deleteById(Long id);


}
