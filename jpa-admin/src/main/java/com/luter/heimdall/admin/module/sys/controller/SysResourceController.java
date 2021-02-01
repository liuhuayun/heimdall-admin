package com.luter.heimdall.admin.module.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.luter.heimdall.admin.module.sys.dto.SysResourceDTO;
import com.luter.heimdall.admin.module.sys.entity.SysResourceEntity;
import com.luter.heimdall.admin.module.sys.repository.SysResourceRepository;
import com.luter.heimdall.admin.module.sys.service.SysResourceService;
import com.luter.heimdall.admin.module.sys.vo.SysResourceVO;
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
 * 系统资源 控制器
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/resource")
@Api(value = "系统资源", tags = "系统资源")
public class SysResourceController extends BaseJpaController {

    private final SysResourceService sysResourceService;
    private final SysResourceRepository sysResourceRepository;

    /**
     * 根据ID查询单条系统资源记录详情
     *
     * @param id 对象唯一ID
     * @return 单个对象DTO
     */
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据ID查询单条系统资源记录详情", notes = "根据ID查询单条系统资源记录详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条系统资源记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysResourceDTO>> getById(@PathVariable Long id) {
        SysResourceDTO data = sysResourceService.getById(id);
        return ResponseUtils.ok(data);
    }

    /**
     * 系统资源列表分页排序查询
     *
     * @return 统一分页列表数据
     */
    @GetMapping("/list")
    @ApiOperation(value = "系统资源列表数据查询", notes = "系统资源列表数据查询", response = PageDTO.class)
    @SysLog(value = "系统资源列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysResourceDTO>>> list(SysResourceVO param, PagerVO pager) {
        PageDTO<SysResourceDTO> list = sysResourceService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    /**
     * 新增系统资源数据
     *
     * @param param 新增对象DTO
     * @return 新增结果
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增系统资源数据", notes = "新增系统资源数据", response = ResponseVO.class)
    @SysLog(value = "新增系统资源数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysResourceDTO>> save(@Validated @RequestBody SysResourceVO param) {
        SysResourceDTO data = sysResourceService.save(param);
        return ResponseUtils.ok("新增成功", data);
    }

    /**
     * 修改系统资源数据
     *
     * @param param 修改对象DTO
     * @return 修改结果
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改系统资源数据", notes = "修改系统资源数据", response = ResponseVO.class)
    @SysLog(value = "修改系统资源数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysResourceVO param) {
        sysResourceService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    /**
     * 删除系统资源数据
     *
     * @param id 对象唯一ID
     * @return 删除成功或失败
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除系统资源数据", notes = "删除系统资源数据", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条系统资源记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysResourceService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取系统资源树", notes = "获取系统资源树", response = ResponseVO.class)
    @SysLog(value = "获取系统资源树", type = BizType.LIST)
    public ResponseEntity<ResponseVO<SysResourceDTO>> tree(SysResourceVO param) {
        return ResponseUtils.ok(sysResourceService.getAllResourceTree(param));
    }

    /**
     * 判断系统资源某个属性值是否存在
     */
    @PostMapping("/exist")
    @ApiOperation(value = "判断系统资源某个属性值是否存在", notes = "判断系统资源某个属性值是否存在", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Boolean>> isExisted(String prop, String value) {
        if (null == prop || StrUtil.isEmpty(value)) {
            return ResponseUtils.fail("参数错误,属性名称和值不能为空", null);
        }
        return ResponseUtils.ok("", sysResourceService.isExist(SysResourceEntity.class, prop, value));
    }
}


