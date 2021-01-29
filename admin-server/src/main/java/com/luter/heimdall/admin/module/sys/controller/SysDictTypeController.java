package com.luter.heimdall.admin.module.sys.controller;


import com.luter.heimdall.admin.module.sys.dto.SysDictTypeDTO;
import com.luter.heimdall.admin.module.sys.entity.SysDictTypeEntity;
import com.luter.heimdall.admin.module.sys.service.SysDictTypeService;
import com.luter.heimdall.admin.module.sys.vo.SysDictTypeVO;
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

@Slf4j
@RestController
@RequestMapping("/sys/dict/type")
@Api(value = "字典分类", tags = "字典分类")
@RequiredArgsConstructor
public class SysDictTypeController extends BaseJpaController {

    private final SysDictTypeService sysDictTypeService;


    @GetMapping("/detail/{id}")
    @ApiOperation(value = "获取数据详情", notes = "获取数据详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysDictTypeDTO>> getById(@PathVariable Long id) {
        SysDictTypeDTO data = sysDictTypeService.getById(id);
        return ResponseUtils.ok(data);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表数据查询", notes = "列表数据查询", response = PageDTO.class)
    @SysLog(value = "列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysDictTypeDTO>>> list(SysDictTypeVO param, PagerVO pager) {
        PageDTO<SysDictTypeDTO> list = sysDictTypeService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    @GetMapping("/list/all")
    @ApiOperation(value = "查询所有字典数据", notes = "查询所有字典数据", response = PageDTO.class)
    @SysLog(value = "查询所有字典数据", type = BizType.LIST)
    public ResponseEntity<ResponseVO<List<SysDictTypeDTO>>> listAll() {
        List<SysDictTypeDTO> list = sysDictTypeService.listAll();
        return ResponseUtils.ok(list);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增数据", notes = "新增数据", response = ResponseVO.class)
    @SysLog(value = "新增数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysDictTypeDTO>> save(@Validated @RequestBody SysDictTypeVO param) {
        SysDictTypeDTO data = sysDictTypeService.save(param);
        return ResponseUtils.ok("新增成功", data);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改数据", notes = "修改数据", response = ResponseVO.class)
    @SysLog(value = "修改数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysDictTypeVO param) {
        sysDictTypeService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysDictTypeService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    @PostMapping("/exist")
    @ApiOperation(value = "判断某个属性值是否存在", notes = "判断某个属性值是否存在", response = ResponseVO.class)
    public ResponseEntity<ResponseVO<Boolean>> isExisted(String prop, String value) {
        return ResponseUtils.ok("", sysDictTypeService.isExist(SysDictTypeEntity.class, prop, value));
    }
}


