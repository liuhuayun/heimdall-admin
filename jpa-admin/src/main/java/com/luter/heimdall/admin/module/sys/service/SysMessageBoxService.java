package com.luter.heimdall.admin.module.sys.service;

import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysMessageBoxDTO;
import com.luter.heimdall.admin.module.sys.vo.SysMessageBoxVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

/**
 * 系统信箱 服务接口
 */
public interface SysMessageBoxService extends BaseService {


    /**
     * 列表查询
     *
     * @param param 查询参数VO
     * @return DTO列表数据
     */
    PageDTO<SysMessageBoxDTO> list(SysMessageBoxVO param, PagerVO pagerVO);

    /**
     * 根据ID获取单个对象
     *
     * @param id 唯一ID
     * @return 单个DTO对象
     */
    SysMessageBoxDTO getById(Long id);

    /**
     * 新增
     *
     * @param param 请求参数
     * @return 新增成功后的DTO对象
     */
    SysMessageBoxDTO save(SysMessageBoxVO param);

    /**
     * 修改
     *
     * @param param 请求参数
     */
    void update(SysMessageBoxVO param);

    /**
     * 删除
     *
     * @param id 唯一ID
     * @return 删除数量，1成功，其他错误
     */
    int deleteById(Long id);


}
