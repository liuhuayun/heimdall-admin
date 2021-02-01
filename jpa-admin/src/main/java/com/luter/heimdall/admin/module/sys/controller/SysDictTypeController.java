package com.luter.heimdall.admin.module.sys.controller;


import cn.hutool.core.util.StrUtil;
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

/**
 * 字典分类 控制器
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/dict/type")
@Api(value = "字典分类", tags = "字典分类")
public class SysDictTypeController extends BaseJpaController {

    private final SysDictTypeService sysDictTypeService;

    /**
     * 根据ID查询单条字典分类记录详情
     *
     * @param id 对象唯一ID
     * @return 单个对象DTO
     */
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据ID查询单条字典分类记录详情", notes = "根据ID查询单条字典分类记录详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条字典分类记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysDictTypeDTO>> getById(@PathVariable Long id) {
        return ResponseUtils.ok(sysDictTypeService.getById(id));
    }

    /**
     * 字典分类列表分页排序查询
     *
     * @return 统一分页列表数据
     */
    @GetMapping("/list")
    @ApiOperation(value = "字典分类列表数据查询", notes = "字典分类列表数据查询", response = PageDTO.class)
    @SysLog(value = "字典分类列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysDictTypeDTO>>> list(SysDictTypeVO param, PagerVO pager) {
        return ResponseUtils.ok(sysDictTypeService.list(param, pager));
    }

    @GetMapping("/list/all")
    @ApiOperation(value = "查询所有字典数据", notes = "查询所有字典数据", response = PageDTO.class)
    @SysLog(value = "查询所有字典数据", type = BizType.LIST)
    public ResponseEntity<ResponseVO<List<SysDictTypeDTO>>> listAll() {
        return ResponseUtils.ok(sysDictTypeService.listAll());
    }

    /**
     * 新增字典分类数据
     *
     * @param param 新增对象DTO
     * @return 新增结果
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增字典分类数据", notes = "新增字典分类数据", response = ResponseVO.class)
    @SysLog(value = "新增字典分类数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysDictTypeDTO>> save(@Validated @RequestBody SysDictTypeVO param) {
        return ResponseUtils.ok("新增成功", sysDictTypeService.save(param));
    }

    /**
     * 修改字典分类数据
     *
     * @param param 修改对象DTO
     * @return 修改结果
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改字典分类数据", notes = "修改字典分类数据", response = ResponseVO.class)
    @SysLog(value = "修改字典分类数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysDictTypeVO param) {
        sysDictTypeService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    /**
     * 删除字典分类数据
     *
     * @param id 对象唯一ID
     * @return 删除成功或失败
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除字典分类数据", notes = "删除字典分类数据", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条字典分类记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysDictTypeService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    /**
     * 判断字典分类某个属性值是否存在
     */
    @PostMapping("/exist")
    @ApiOperation(value = "判断字典分类某个属性值是否存在", notes = "判断字典分类某个属性值是否存在", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Boolean>> isExisted(String prop, String value) {
        if (null == prop || StrUtil.isEmpty(value)) {
            return ResponseUtils.fail("参数错误,属性名称和值不能为空", null);
        }
        return ResponseUtils.ok("", sysDictTypeService.isExist(SysDictTypeEntity.class, prop, value));
    }
}


