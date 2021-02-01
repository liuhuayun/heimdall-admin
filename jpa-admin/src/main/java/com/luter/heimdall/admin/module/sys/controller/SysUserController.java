package com.luter.heimdall.admin.module.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.luter.heimdall.admin.module.sys.dto.SysResourceDTO;
import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.admin.module.sys.entity.SysUserEntity;
import com.luter.heimdall.admin.module.sys.repository.SysRoleRepository;
import com.luter.heimdall.admin.module.sys.service.SysRoleService;
import com.luter.heimdall.admin.module.sys.service.SysUserService;
import com.luter.heimdall.admin.module.sys.vo.SysUserVO;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户 控制器
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/user")
@Api(value = "系统用户", tags = "系统用户")
public class SysUserController extends BaseJpaController {

    private final SysUserService sysUserService;

    /**
     * 根据ID查询单条系统用户记录详情
     *
     * @param id 对象唯一ID
     * @return 单个对象DTO
     */
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据ID查询单条系统用户记录详情", notes = "根据ID查询单条系统用户记录详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条系统用户记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysUserDTO>> getById(@PathVariable Long id) {
        SysUserDTO data = sysUserService.getById(id);
        return ResponseUtils.ok(data);
    }

    /**
     * 系统用户列表分页排序查询
     *
     * @return 统一分页列表数据
     */
    @GetMapping("/list")
    @ApiOperation(value = "系统用户列表数据查询", notes = "系统用户列表数据查询", response = PageDTO.class)
    @SysLog(value = "系统用户列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysUserDTO>>> list(SysUserVO param, PagerVO pager) {
        PageDTO<SysUserDTO> list = sysUserService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    /**
     * 新增系统用户数据
     *
     * @param param 新增对象DTO
     * @return 新增结果
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增系统用户数据", notes = "新增系统用户数据", response = ResponseVO.class)
    @SysLog(value = "新增系统用户数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysUserDTO>> save(@Validated @RequestBody SysUserVO param) {
        SysUserDTO data = sysUserService.save(param);
        return ResponseUtils.ok("新增成功", data);
    }

    /**
     * 修改系统用户数据
     *
     * @param param 修改对象DTO
     * @return 修改结果
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改系统用户数据", notes = "修改系统用户数据", response = ResponseVO.class)
    @SysLog(value = "修改系统用户数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysUserVO param) {
        sysUserService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    /**
     * 删除系统用户数据
     *
     * @param id 对象唯一ID
     * @return 删除成功或失败
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除系统用户数据", notes = "删除系统用户数据", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条系统用户记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysUserService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "系统登录", notes = "系统登录", response = ResponseVO.class)
    @SysLog(value = "系统登录", type = BizType.LOGIN)
    public ResponseEntity<ResponseVO<Serializable>> login(@RequestBody SysUserVO user) {
        return ResponseUtils.ok(sysUserService.login(user));
    }


    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出系统登录", notes = "退出系统登录", response = ResponseVO.class)
    @SysLog(value = "退出系统登录", type = BizType.LOGOUT)
    public ResponseEntity<ResponseVO<Void>> logout() {
        sysUserService.logout();
        return ResponseUtils.ok("退出成功");
    }

    @GetMapping(value = "/current")
    @ApiOperation(value = "获取当前登录用户", notes = "获取当前登录用户")
    @SysLog
    public ResponseEntity<ResponseVO<SysUserDTO>> getCurrentUserInfo() {
        final SysUserDTO currentUserFromDb = sysUserService.getCurrentUserDTO(true, false);
        return ResponseUtils.ok(sysUserService.getById(currentUserFromDb.getId()));
    }

    @GetMapping(value = "/current/menus")
    @ApiOperation(value = "获取当前登录用户的菜单", notes = "获取当前登录用户的菜单")
    @SysLog
    public ResponseEntity<ResponseVO<List<SysResourceDTO>>> getCurrentUserMenus() {
        final SysUserDTO currentUser = sysUserService.getCurrentUserDTO(true, false);
        final SysResourceDTO userMenuTree = sysUserService.getUserMenuTree(currentUser.getId());
        if (null != userMenuTree && !userMenuTree.getChildren().isEmpty()) {
            return ResponseUtils.ok("", sysUserService.getUserMenuTree(currentUser.getId()).getChildren());
        }
        return ResponseUtils.ok("", new ArrayList<>());
    }


    @PostMapping("/update/locked")
    @ApiOperation(value = "解锁/锁定用户", notes = "解锁/锁定用户", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Void>> uploadLocked(@RequestBody SysUserVO userDTO) {
        if (null != userDTO.getLocked()) {
            final SysUserEntity user = sysUserService.getEntityById(SysUserEntity.class, userDTO.getId());
            user.setLocked(userDTO.getLocked());
            sysUserService.updateEntity(user);
            return ResponseUtils.ok("用户已:" + (user.getLocked() ? "锁定" : "解锁"));
        }
        return ResponseUtils.fail("操作失败，请检查参数." + userDTO.getLocked());
    }

    @PostMapping("/password/reset")
    @ApiOperation(value = "重置用户的密码为默认密码", notes = "重置用户的密码为默认密码", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Void>> resetPassWord(@RequestBody SysUserVO param) {
        sysUserService.resetPassword(param);
        return ResponseUtils.ok("用户" + param.getUsername() + "的密码已经重置为:" + param.getPassword() + ",请使用新密码重新登录系统");
    }

    @PostMapping("/current/update")
    @ApiOperation(value = "当前登录用户修改个人信息", notes = "当前登录用户修改个人信息", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Void>> changeUserInfo(@Validated @RequestBody SysUserVO param) {
        SysUserEntity user = sysUserService.getCurrentUserEntity(true);
        user.setAddress(param.getAddress());
        user.setGender(param.getGender());
        user.setNickName(param.getNickName());
        user.setRealName(param.getRealName());
        sysUserService.updateEntity(user);
        return ResponseUtils.ok("修改成功");
    }


    /**
     * 判断系统用户某个属性值是否存在
     */
    @PostMapping("/exist")
    @ApiOperation(value = "判断系统用户某个属性值是否存在", notes = "判断系统用户某个属性值是否存在", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Boolean>> isExisted(String prop, String value) {
        if (null == prop || StrUtil.isEmpty(value)) {
            return ResponseUtils.fail("参数错误,属性名称和值不能为空", null);
        }
        return ResponseUtils.ok("", sysUserService.isExist(SysUserEntity.class, prop, value));
    }
}


