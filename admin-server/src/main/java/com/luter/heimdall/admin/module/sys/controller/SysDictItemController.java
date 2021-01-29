package com.luter.heimdall.admin.module.sys.controller;


import cn.hutool.core.util.StrUtil;

import com.luter.heimdall.admin.module.sys.dto.SysDictItemDTO;
import com.luter.heimdall.admin.module.sys.service.SysDictItemService;
import com.luter.heimdall.admin.module.sys.vo.SysDictItemVO;
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
@RequestMapping("/sys/dict/item")
@Api(value = "字典条目", tags = "字典条目")
@RequiredArgsConstructor
public class SysDictItemController extends BaseJpaController {

    private final SysDictItemService sysDictItemService;


    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据ID查询单条字典条目记录详情", notes = "根据ID查询单条字典条目记录详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条字典条目记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysDictItemDTO>> getById(@PathVariable Long id) {
        SysDictItemDTO data = sysDictItemService.getById(id);
        return ResponseUtils.ok(data);
    }

    @GetMapping("/list")
    @ApiOperation(value = "字典条目列表分页排序查询", notes = "字典条目列表分页排序查询", response = PageDTO.class)
    @SysLog(value = "字典条目列表分页排序查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysDictItemDTO>>> list(SysDictItemVO param, PagerVO pager) {
        PageDTO<SysDictItemDTO> list = sysDictItemService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    @GetMapping("/list/type")
    @ApiOperation(value = "根据分类ID获取字典分类下所有数据", notes = "根据分类ID获取字典分类下所有数据", response = PageDTO.class)
    @SysLog(value = "根据分类ID获取字典分类下所有数据", type = BizType.LIST)
    public ResponseEntity<ResponseVO<List<SysDictItemDTO>>> listAll(SysDictItemVO param) {
        List<SysDictItemDTO> list = sysDictItemService.listByTypeId(param.getTypeId());
        return ResponseUtils.ok(list);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增字典条目数据", notes = "新增字典条目数据", response = ResponseVO.class)
    @SysLog(value = "新增字典条目数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysDictItemDTO>> save(@Validated @RequestBody SysDictItemVO param) {
        SysDictItemDTO data = sysDictItemService.save(param);
        return ResponseUtils.ok("新增成功", data);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改字典条目数据", notes = "修改字典条目数据", response = ResponseVO.class)
    @SysLog(value = "修改字典条目数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysDictItemVO param) {
        sysDictItemService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除单条字典条目记录", notes = "根据ID删除单条字典条目记录", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条字典条目记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysDictItemService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    @PostMapping("/exist")
    @ApiOperation(value = "判断字典项是否已经存在", notes = "判断字典项是否已经存在", response = ResponseVO.class)
    public ResponseEntity<ResponseVO<Boolean>> isExistUsername(Long typeId, String itemValue) {
        if (null == typeId || StrUtil.isEmpty(itemValue)) {
            return ResponseUtils.fail("参数错误", null);
        }
        String hql = "select count(*) from  SysDictItemEntity where typeId=?0 and itemValue=?1";
        final Long countByHql = sysDictItemService.getCountByHql(hql, typeId, itemValue);
        return ResponseUtils.ok("", countByHql > 0);
    }

    @PostMapping("/byname/{typeName}")
    @ApiOperation(value = "根据字典分类获取字典项列表", notes = "根据字典分类获取字典项列表", response = ResponseVO.class)
    @SysLog(value = "根据字典分类获取字典项列表", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<List<SysDictItemDTO>>> getByTypeName(@PathVariable String typeName) {
        List<SysDictItemDTO> data = sysDictItemService.listByTypeName(typeName);
        return ResponseUtils.ok(data);
    }

    @PostMapping("/byid/{typeId}")
    @ApiOperation(value = "根据字典分类获取字典项列表", notes = "根据字典分类获取字典项列表", response = ResponseVO.class)
    @SysLog(value = "根据字典分类获取字典项列表", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<List<SysDictItemDTO>>> getByTypeId(@PathVariable Long typeId) {
        List<SysDictItemDTO> data = sysDictItemService.listByTypeId(typeId);
        return ResponseUtils.ok(data);
    }

}


