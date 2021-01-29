package com.luter.heimdall.admin.module.sys.service;


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysResourceDTO;
import com.luter.heimdall.admin.module.sys.entity.SysResourceEntity;
import com.luter.heimdall.admin.module.sys.vo.SysResourceVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.util.List;
import java.util.Map;

public interface SysResourceService extends BaseService {


    PageDTO<SysResourceDTO> list(SysResourceVO param, PagerVO pagerVO);

    List<SysResourceEntity> listAll();

    SysResourceDTO getAllResourceTree(SysResourceVO param);

    SysResourceDTO getById(Long id);

    SysResourceDTO save(SysResourceVO param);

    void update(SysResourceVO param);

    int deleteById(Long id);

    Map<String, Object> getRoleAuthTree(Long roleId);
}
