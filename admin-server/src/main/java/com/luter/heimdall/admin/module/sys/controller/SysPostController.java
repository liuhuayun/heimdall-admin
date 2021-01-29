package com.luter.heimdall.admin.module.sys.controller;


import cn.hutool.core.util.StrUtil;

import com.luter.heimdall.admin.module.sys.dto.SysPostDTO;
import com.luter.heimdall.admin.module.sys.entity.SysPostEntity;
import com.luter.heimdall.admin.module.sys.service.SysPostService;
import com.luter.heimdall.admin.module.sys.vo.SysPostVO;
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

@Slf4j
@RestController
@RequestMapping("/sys/post")
@Api(value = "岗位", tags = "岗位")
@RequiredArgsConstructor
public class SysPostController extends BaseJpaController {

    private final SysPostService sysPostService;



    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据ID查询单条岗位记录详情", notes = "根据ID查询单条岗位记录详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条岗位记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysPostDTO>> getById(@PathVariable Long id) {
        SysPostDTO data = sysPostService.getById(id);
        return ResponseUtils.ok(data);
    }

    @GetMapping("/list")
    @ApiOperation(value = "岗位列表数据查询", notes = "岗位列表数据查询", response = PageDTO.class)
    @SysLog(value = "岗位列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysPostDTO>>> list(SysPostVO param, PagerVO pager) {
        PageDTO<SysPostDTO> list = sysPostService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增岗位数据", notes = "新增岗位数据", response = ResponseVO.class)
    @SysLog(value = "新增岗位数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysPostDTO>> save(@Validated @RequestBody SysPostVO param) {

        SysPostDTO data = sysPostService.save(param);
        return ResponseUtils.ok("新增成功", data);

    }

    @PostMapping("/update")
    @ApiOperation(value = "修改岗位数据", notes = "修改岗位数据", response = ResponseVO.class)
    @SysLog(value = "修改岗位数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysPostVO param) {
        sysPostService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除岗位数据", notes = "删除岗位数据", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条岗位记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysPostService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    @PostMapping("/exist")
    @ApiOperation(value = "判断岗位某个属性值是否存在", notes = "判断岗位某个属性值是否存在", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Boolean>> isExisted(String prop, String value) {
        if (null == prop || StrUtil.isEmpty(value)) {
            return ResponseUtils.fail("参数错误,属性名称和值不能为空", null);
        }
        return ResponseUtils.ok("", sysPostService.isExist(SysPostEntity.class, prop, value));
    }
}


