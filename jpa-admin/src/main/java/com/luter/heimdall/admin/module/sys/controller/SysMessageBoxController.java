package com.luter.heimdall.admin.module.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.luter.heimdall.admin.module.sys.dto.SysMessageBoxDTO;
import com.luter.heimdall.admin.module.sys.entity.SysMessageBoxEntity;
import com.luter.heimdall.admin.module.sys.service.SysMessageBoxService;
import com.luter.heimdall.admin.module.sys.vo.SysMessageBoxVO;
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

/**
 * 系统信箱 控制器
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/message/box")
@Api(value = "系统信箱", tags = "系统信箱")
public class SysMessageBoxController extends BaseJpaController {

    private final SysMessageBoxService sysMessageBoxService;

    /**
     * 根据ID查询单条系统信箱记录详情
     *
     * @param id 对象唯一ID
     * @return 单个对象DTO
     */
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据ID查询单条系统信箱记录详情", notes = "根据ID查询单条系统信箱记录详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条系统信箱记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysMessageBoxDTO>> getById(@PathVariable Long id) {
        SysMessageBoxDTO data = sysMessageBoxService.getById(id);
        return ResponseUtils.ok(data);
    }

    /**
     * 系统信箱列表分页排序查询
     *
     * @return 统一分页列表数据
     */
    @GetMapping("/list")
    @ApiOperation(value = "系统信箱列表数据查询", notes = "系统信箱列表数据查询", response = PageDTO.class)
    @SysLog(value = "系统信箱列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysMessageBoxDTO>>> list(SysMessageBoxVO param, PagerVO pager) {
        PageDTO<SysMessageBoxDTO> list = sysMessageBoxService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    @GetMapping("/list/current")
    @ApiOperation(value = "获取当前用户的站内信", notes = "获取当前用户的站内信", response = PageDTO.class)
    @SysLog(value = "获取当前用户的站内信", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysMessageBoxDTO>>> listCurrentUserMsgs() {
        SysMessageBoxVO param = new SysMessageBoxVO();
        param.setReceiverId(sysMessageBoxService.getCurrentUserDTO(true, false).getId());
        PagerVO pager = new PagerVO();
        pager.setAsc("receivedTime");
        pager.setPage(1);
        pager.setSize(10);
        final PageDTO<SysMessageBoxDTO> list = sysMessageBoxService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    /**
     * 新增系统信箱数据
     *
     * @param param 新增对象DTO
     * @return 新增结果
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增系统信箱数据", notes = "新增系统信箱数据", response = ResponseVO.class)
    @SysLog(value = "新增系统信箱数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysMessageBoxDTO>> save(@Validated @RequestBody SysMessageBoxVO param) {
        SysMessageBoxDTO data = sysMessageBoxService.save(param);
        return ResponseUtils.ok("新增成功", data);
    }

    /**
     * 修改系统信箱数据
     *
     * @param param 修改对象DTO
     * @return 修改结果
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改系统信箱数据", notes = "修改系统信箱数据", response = ResponseVO.class)
    @SysLog(value = "修改系统信箱数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysMessageBoxVO param) {
        sysMessageBoxService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    /**
     * 删除系统信箱数据
     *
     * @param id 对象唯一ID
     * @return 删除成功或失败
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除系统信箱数据", notes = "删除系统信箱数据", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条系统信箱记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysMessageBoxService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    /**
     * 判断系统信箱某个属性值是否存在
     */
    @PostMapping("/exist")
    @ApiOperation(value = "判断系统信箱某个属性值是否存在", notes = "判断系统信箱某个属性值是否存在", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Boolean>> isExisted(String prop, String value) {
        if (null == prop || StrUtil.isEmpty(value)) {
            return ResponseUtils.fail("参数错误,属性名称和值不能为空", null);
        }
        return ResponseUtils.ok("", sysMessageBoxService.isExist(SysMessageBoxEntity.class, prop, value));
    }
}


