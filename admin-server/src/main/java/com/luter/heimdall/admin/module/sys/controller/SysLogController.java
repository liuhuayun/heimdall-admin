package com.luter.heimdall.admin.module.sys.controller;


import com.luter.heimdall.admin.module.sys.dto.SysLogDTO;
import com.luter.heimdall.admin.module.sys.service.SysLogService;
import com.luter.heimdall.admin.module.sys.vo.SysLogVO;
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

@Slf4j
@RestController
@RequestMapping("/sys/log")
@Api(value = "系统日志", tags = "系统日志")
@RequiredArgsConstructor
public class SysLogController extends BaseJpaController {

    private final SysLogService sysLogService;


    @GetMapping("/detail/{id}")
    @ApiOperation(value = "获取数据详情", notes = "获取数据详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysLogDTO>> getById(@PathVariable Long id) {
        SysLogDTO data = sysLogService.getById(id);
        return ResponseUtils.ok(data);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表数据查询", notes = "列表数据查询", response = PageDTO.class)
    @SysLog(value = "列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysLogDTO>>> list(SysLogVO param, PagerVO pager) {
        PageDTO<SysLogDTO> list = sysLogService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增数据", notes = "新增数据", response = ResponseVO.class)
    @SysLog(value = "新增数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysLogDTO>> save(@Validated @RequestBody SysLogVO param) {
        SysLogDTO data = sysLogService.save(param);
        return ResponseUtils.ok("新增成功", data);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改数据", notes = "修改数据", response = ResponseVO.class)
    @SysLog(value = "修改数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysLogVO param) {
        sysLogService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysLogService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    @PostMapping("/batch/delete")
    @ApiOperation(value = "批量删除数据", notes = "批量删除数据", response = ResponseVO.class)
    @SysLog(value = "批量删除日志数据", type = BizType.BATCH_DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@RequestBody List<Long> ids) {
        final int i = sysLogService.deleteByBatch(ids);
        return ResponseUtils.ok("删除成功" + i);
    }

    @GetMapping("/chart/hour")
    @ApiOperation(value = "按日期时间获取日志数量", notes = "按日期时间获取日志数量", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<List<Map<Object, Object>>>> chartHour() {
        String sql = "SELECT   " +
                "    DATE_FORMAT(e.request_time, '%Y%m%d %H') AS time,  " +
                "    COUNT(*) AS count  " +
                "FROM  " +
                "    sys_log e  " +
                "GROUP BY DATE_FORMAT(e.request_time, '%Y%m%d %H')  " +
                "ORDER BY DATE_FORMAT(e.request_time, '%Y%m%d %H');";

        final List<Map<Object, Object>> maps = sysLogService.listBySql(sql);

        return ResponseUtils.ok(maps);
    }
}


