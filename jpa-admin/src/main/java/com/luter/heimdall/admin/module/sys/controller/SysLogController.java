package com.luter.heimdall.admin.module.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.luter.heimdall.admin.module.sys.dto.SysLogDTO;
import com.luter.heimdall.admin.module.sys.entity.SysLogEntity;
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

/**
 * 系统日志 控制器
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/log")
@Api(value = "系统日志", tags = "系统日志")
public class SysLogController extends BaseJpaController {

    private final SysLogService sysLogService;

    /**
     * 根据ID查询单条系统日志记录详情
     *
     * @param id 对象唯一ID
     * @return 单个对象DTO
     */
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据ID查询单条系统日志记录详情", notes = "根据ID查询单条系统日志记录详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条系统日志记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysLogDTO>> getById(@PathVariable Long id) {
        SysLogDTO data = sysLogService.getById(id);
        return ResponseUtils.ok(data);
    }

    /**
     * 系统日志列表分页排序查询
     *
     * @return 统一分页列表数据
     */
    @GetMapping("/list")
    @ApiOperation(value = "系统日志列表数据查询", notes = "系统日志列表数据查询", response = PageDTO.class)
    @SysLog(value = "系统日志列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysLogDTO>>> list(SysLogVO param, PagerVO pager) {
        PageDTO<SysLogDTO> list = sysLogService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    /**
     * 新增系统日志数据
     *
     * @param param 新增对象DTO
     * @return 新增结果
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增系统日志数据", notes = "新增系统日志数据", response = ResponseVO.class)
    @SysLog(value = "新增系统日志数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysLogDTO>> save(@Validated @RequestBody SysLogVO param) {
        SysLogDTO data = sysLogService.save(param);
        return ResponseUtils.ok("新增成功", data);
    }

    /**
     * 修改系统日志数据
     *
     * @param param 修改对象DTO
     * @return 修改结果
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改系统日志数据", notes = "修改系统日志数据", response = ResponseVO.class)
    @SysLog(value = "修改系统日志数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysLogVO param) {
        sysLogService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    /**
     * 删除系统日志数据
     *
     * @param id 对象唯一ID
     * @return 删除成功或失败
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除系统日志数据", notes = "删除系统日志数据", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条系统日志记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysLogService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    /**
     * 判断系统日志某个属性值是否存在
     */
    @PostMapping("/exist")
    @ApiOperation(value = "判断系统日志某个属性值是否存在", notes = "判断系统日志某个属性值是否存在", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Boolean>> isExisted(String prop, String value) {
        if (null == prop || StrUtil.isEmpty(value)) {
            return ResponseUtils.fail("参数错误,属性名称和值不能为空", null);
        }
        return ResponseUtils.ok("", sysLogService.isExist(SysLogEntity.class, prop, value));
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


