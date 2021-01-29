package com.luter.heimdall.admin.module.sys.service;


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysDepartmentDTO;
import com.luter.heimdall.admin.module.sys.vo.SysDepartmentVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

public interface SysDepartmentService extends BaseService {


    PageDTO<SysDepartmentDTO> list(SysDepartmentVO param, PagerVO pagerVO);

    SysDepartmentDTO getById(Long id);

    SysDepartmentDTO save(SysDepartmentVO param);

    void update(SysDepartmentVO param);

    int deleteById(Long id);


    SysDepartmentDTO getDepartmentTree(SysDepartmentVO param);
}
