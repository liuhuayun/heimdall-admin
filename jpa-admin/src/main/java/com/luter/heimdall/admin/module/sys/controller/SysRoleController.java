package com.luter.heimdall.admin.module.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.luter.heimdall.admin.module.sys.dto.SysResourceDTO;
import com.luter.heimdall.admin.module.sys.dto.SysRoleDTO;
import com.luter.heimdall.admin.module.sys.entity.SysRoleEntity;
import com.luter.heimdall.admin.module.sys.mapper.SysRoleMapper;
import com.luter.heimdall.admin.module.sys.service.SysResourceService;
import com.luter.heimdall.admin.module.sys.service.SysRoleService;
import com.luter.heimdall.admin.module.sys.vo.SysRoleVO;
import com.luter.heimdall.starter.jpa.base.controller.BaseJpaController;
import com.luter.heimdall.starter.model.base.ResponseVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.syslog.annotation.SysLog;
import com.luter.heimdall.starter.syslog.enums.BizType;
import com.luter.heimdall.starter.utils.response.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统角色 控制器
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/role")
@Api(value = "系统角色", tags = "系统角色")
public class SysRoleController extends BaseJpaController {

    private final SysRoleService sysRoleService;
    private final SysRoleMapper sysRoleMapper;

    private final SysResourceService sysResourceService;

    /**
     * 根据ID查询单条系统角色记录详情
     *
     * @param id 对象唯一ID
     * @return 单个对象DTO
     */
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据ID查询单条系统角色记录详情", notes = "根据ID查询单条系统角色记录详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条系统角色记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysRoleDTO>> getById(@PathVariable Long id) {
        SysRoleDTO data = sysRoleService.getById(id);
        return ResponseUtils.ok(data);
    }

    @GetMapping("/list/all")
    @ApiOperation(value = "获取所有角色列表数据", notes = "获取所有角色列表数据", response = PageDTO.class)
    @SysLog(value = "获取所有角色列表数据", type = BizType.LIST)
    public ResponseEntity<ResponseVO<List<SysRoleDTO>>> listAll() {
        return ResponseUtils.ok(sysRoleMapper.entityListToDTOList(sysRoleService.listAll(SysRoleEntity.class)));
    }

    /**
     * 系统角色列表分页排序查询
     *
     * @return 统一分页列表数据
     */
    @GetMapping("/list")
    @ApiOperation(value = "系统角色列表数据查询", notes = "系统角色列表数据查询", response = PageDTO.class)
    @SysLog(value = "系统角色列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysRoleDTO>>> list(SysRoleVO param, PagerVO pager) {
        PageDTO<SysRoleDTO> list = sysRoleService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    /**
     * 新增系统角色数据
     *
     * @param param 新增对象DTO
     * @return 新增结果
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增系统角色数据", notes = "新增系统角色数据", response = ResponseVO.class)
    @SysLog(value = "新增系统角色数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysRoleDTO>> save(@Validated @RequestBody SysRoleVO param) {
        SysRoleDTO data = sysRoleService.save(param);
        return ResponseUtils.ok("新增成功", data);
    }

    /**
     * 修改系统角色数据
     *
     * @param param 修改对象DTO
     * @return 修改结果
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改系统角色数据", notes = "修改系统角色数据", response = ResponseVO.class)
    @SysLog(value = "修改系统角色数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysRoleVO param) {
        sysRoleService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    /**
     * 删除系统角色数据
     *
     * @param id 对象唯一ID
     * @return 删除成功或失败
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除系统角色数据", notes = "删除系统角色数据", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条系统角色记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysRoleService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    /**
     * 判断系统角色某个属性值是否存在
     */
    @PostMapping("/exist")
    @ApiOperation(value = "判断系统角色某个属性值是否存在", notes = "判断系统角色某个属性值是否存在", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Boolean>> isExisted(String prop, String value) {
        if (null == prop || StrUtil.isEmpty(value)) {
            return ResponseUtils.fail("参数错误,属性名称和值不能为空", null);
        }
        return ResponseUtils.ok("", sysRoleService.isExist(SysRoleEntity.class, prop, value));
    }

    @PostMapping("/auth/tree")
    @ApiOperation(value = "获取角色具有的资源树", notes = "获取角色具有的资源树", response = ResponseVO.class)
    @SysLog(value = "获取角色具有的资源树", type = BizType.LIST)
    public ResponseEntity<ResponseVO<Map<String, Object>>> getAuthTree(Long roleId) {
        return ResponseUtils.ok(sysResourceService.getRoleAuthTree(roleId));
    }

    @PostMapping("/auth/save")
    @ApiOperation(value = "保存角色授权信息", notes = "保存角色授权信息", response = ResponseVO.class)
    @SysLog(value = "保存角色授权信息", type = BizType.LIST)
    public ResponseEntity<ResponseVO<Void>> saveRoleAuthInfo(@RequestBody SysRoleVO params) {
        sysRoleService.saveRoleAuth(params);
        return ResponseUtils.ok("角色授权成功");
    }
}


