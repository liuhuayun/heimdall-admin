package com.luter.heimdall.admin.module.sys.service;

import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysLogDTO;
import com.luter.heimdall.admin.module.sys.vo.SysLogVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.util.List;

/**
 * 系统日志 服务接口
 */
public interface SysLogService extends BaseService {


    /**
     * 列表查询
     *
     * @param param 查询参数VO
     * @return DTO列表数据
     */
    PageDTO<SysLogDTO> list(SysLogVO param, PagerVO pagerVO);

    /**
     * 根据ID获取单个对象
     *
     * @param id 唯一ID
     * @return 单个DTO对象
     */
    SysLogDTO getById(Long id);

    /**
     * 新增
     *
     * @param param 请求参数
     * @return 新增成功后的DTO对象
     */
    SysLogDTO save(SysLogVO param);

    /**
     * 修改
     *
     * @param param 请求参数
     */
    void update(SysLogVO param);

    /**
     * 删除
     *
     * @param id 唯一ID
     * @return 删除数量，1成功，其他错误
     */
    int deleteById(Long id);

    int deleteByBatch(List<Long> ids);
}
