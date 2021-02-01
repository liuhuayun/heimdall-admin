package com.luter.heimdall.admin.module.sys.service;

import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysDictTypeDTO;
import com.luter.heimdall.admin.module.sys.vo.SysDictTypeVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.util.List;

/**
 * 字典分类 服务接口
 *
 * @author Luter
 */
public interface SysDictTypeService extends BaseService {


    /**
     * 列表查询
     *
     * @param param   查询参数VO
     * @param pagerVO the pager vo
     * @return DTO列表数据 page dto
     */
    PageDTO<SysDictTypeDTO> list(SysDictTypeVO param, PagerVO pagerVO);

    /**
     * List all list.
     *
     * @return the list
     */
    List<SysDictTypeDTO> listAll();

    /**
     * 根据ID获取单个对象
     *
     * @param id 唯一ID
     * @return 单个DTO对象 by id
     */
    SysDictTypeDTO getById(Long id);

    /**
     * 新增
     *
     * @param param 请求参数
     * @return 新增成功后的DTO对象 sys dict type dto
     */
    SysDictTypeDTO save(SysDictTypeVO param);

    /**
     * 修改
     *
     * @param param 请求参数
     */
    void update(SysDictTypeVO param);

    /**
     * 删除
     *
     * @param id 唯一ID
     * @return 删除数量 ，1成功，其他错误
     */
    int deleteById(Long id);


}
