package com.luter.heimdall.admin.module.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.luter.heimdall.admin.module.sys.dto.SysDepartmentDTO;
import com.luter.heimdall.admin.module.sys.entity.SysDepartmentEntity;
import com.luter.heimdall.admin.module.sys.service.SysDepartmentService;
import com.luter.heimdall.admin.module.sys.vo.SysDepartmentVO;
import com.luter.heimdall.starter.jpa.base.controller.BaseJpaController;
import com.luter.heimdall.starter.model.base.ResponseVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.syslog.annotation.SysLog;
import com.luter.heimdall.starter.syslog.enums.BizType;
import com.luter.heimdall.starter.utils.exception.LuterIllegalParameterException;
import com.luter.heimdall.starter.utils.response.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sys/department")
@Api(value = "组织机构", tags = "组织机构")
@RequiredArgsConstructor
public class SysDepartmentController extends BaseJpaController {

    private final SysDepartmentService sysDepartmentService;


    @GetMapping("/detail/{id}")
    @ApiOperation(value = "获取组织机构数据详情", notes = "获取组织机构数据详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询组织机构单条记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysDepartmentDTO>> getById(@PathVariable Long id) {
        SysDepartmentDTO data = sysDepartmentService.getById(id);
        return ResponseUtils.ok(data);
    }

    @GetMapping("/list")
    @ApiOperation(value = "组织机构列表数据查询", notes = "组织机构列表数据查询", response = PageDTO.class)
    @SysLog(value = "组织机构列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysDepartmentDTO>>> list(SysDepartmentVO param, PagerVO pager) {
        PageDTO<SysDepartmentDTO> list = sysDepartmentService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    @GetMapping("/tree")
    @ApiOperation(value = "组织机构树数据查询", notes = "组织机构树数据查询", response = PageDTO.class)
    @SysLog(value = "组织机构树数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<List<SysDepartmentDTO>>> tree(SysDepartmentVO param) {
        SysDepartmentDTO tree = sysDepartmentService.getDepartmentTree(param);
        return ResponseUtils.ok(tree.getChildren());
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增组织机构数据", notes = "新增组织机构数据", response = ResponseVO.class)
    @SysLog(value = "新增组织机构数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysDepartmentDTO>> save(@Validated @RequestBody SysDepartmentVO param) {
        SysDepartmentDTO data = sysDepartmentService.save(param);
        return ResponseUtils.ok("新增成功", data);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改组织机构数据", notes = "修改组织机构数据", response = ResponseVO.class)
    @SysLog(value = "修改数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysDepartmentVO param) {
        sysDepartmentService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除单条组织机构记录", notes = "根据ID删除单条组织机构记录", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条组织机构记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysDepartmentService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    @PostMapping("/exist")
    @ApiOperation(value = "判断组织机构某个属性的值是否存在", notes = "判断组织机构某个属性的值是否存在", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Boolean>> isExisted(String prop, String value) {
        if (null == prop || StrUtil.isEmpty(value)) {
            return ResponseUtils.fail("参数错误,属性名称和值不能为空", null);
        }
        return ResponseUtils.ok("", sysDepartmentService.isExist(SysDepartmentEntity.class, prop, value));


    }

    @PostMapping("/exist/name")
    @ApiOperation(value = "判断组织机构名称是否可用", notes = "判断组织机构名称是否可用", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Boolean>> isExistDeptName(Long pid, String name) {
        if (null == pid || StrUtil.isEmpty(name)) {
            throw new LuterIllegalParameterException("参数错误，上级部门ID和本级部门名称不能为空");
        }
        String hql = "select count(*) from SysDepartmentEntity where pid  =?0 and name = ?1";
        return ResponseUtils.ok("", sysDepartmentService.getCountByHql(hql, pid, name) > 0);
    }
}


