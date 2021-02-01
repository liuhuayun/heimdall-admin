package com.luter.heimdall.admin.module.sys.service;

import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.*;
import com.luter.heimdall.admin.module.sys.vo.SysUserVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.io.Serializable;
import java.util.List;

/**
 * 系统用户 服务接口
 *
 * @author Luter
 */
public interface SysUserService extends BaseService {


    /**
     * 列表查询
     *
     * @param param   查询参数VO
     * @param pagerVO the pager vo
     * @return DTO列表数据 page dto
     */
    PageDTO<SysUserDTO> list(SysUserVO param, PagerVO pagerVO);

    /**
     * 根据ID获取单个对象
     *
     * @param id 唯一ID
     * @return 单个DTO对象 by id
     */
    SysUserDTO getById(Long id);

    /**
     * 新增
     *
     * @param param 请求参数
     * @return 新增成功后的DTO对象 sys user dto
     */
    SysUserDTO save(SysUserVO param);

    /**
     * 加载当前用户菜单
     *
     * @param id the id
     * @return the list
     */
    List<SysResourceDTO> loadUserMenusByUserId(Long id);

    SysResourceDTO getUserMenuTree(Long id);


    List<SysRoleDTO> getUserRoles(Long id);


    List<SysPostDTO> getUserPosts(Long id);

    /**
     * 重置用户密码
     *
     * @param param the param
     */
    void resetPassword(SysUserVO param);

    /**
     * 修改
     *
     * @param param 请求参数
     */
    void update(SysUserVO param);

    /**
     * 删除
     *
     * @param id 唯一ID
     * @return 删除数量 ，1成功，其他错误
     */
    int deleteById(Long id);

    /**
     * Login serializable.
     *
     * @param user the user
     * @return the serializable
     */
    Serializable login(SysUserVO user);

    /**
     * Logout.
     */
    void logout();


}
