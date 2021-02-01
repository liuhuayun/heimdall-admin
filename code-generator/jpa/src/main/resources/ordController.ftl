package ${pkgPath}.controller;


import ${pkgPath}.dto.${className}DTO;
import ${pkgPath}.service.${className}Service;
<#if baseController??>
    import ${baseController};
</#if>

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
* ${moduleDesc} 控制器
*/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${urlName}")
@Api(value = "${moduleDesc}", tags = "${moduleDesc}")
<#if baseController??>
    public class ${className}Controller extends ${baseControllerName} {
<#else>
    public class ${className}Controller {
</#if>

    private final ${className}Service ${LFClassName}Service;

    /**
    * 根据ID查询单条${moduleDesc}记录详情
    *
    * @param id 对象唯一ID
    *
    * @return 单个对象DTO
    */
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据ID查询单条${moduleDesc}记录详情", notes = "根据ID查询单条${moduleDesc}记录详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条${moduleDesc}记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<${className}DTO>> getById(@PathVariable Long id) {
    return ResponseUtils.ok(${LFClassName}Service.getById(id));
    }

    /**
    * ${moduleDesc}列表分页排序查询
    *
    * @return 统一分页列表数据
    */
    @GetMapping("/list")
    @ApiOperation(value = "${moduleDesc}列表数据查询", notes = "${moduleDesc}列表数据查询", response = PageDTO.class)
    @SysLog(value = "${moduleDesc}列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<${className}DTO>>> list(${className}VO param,  PagerVO pager) {
         return ResponseUtils.ok(${LFClassName}Service.list(param,pager));
    }

    /**
    * 新增${moduleDesc}数据
    *
    * @param param 新增对象DTO
    *
    * @return 新增结果
    */
    @PostMapping("/save")
    @ApiOperation(value = "新增${moduleDesc}数据", notes = "新增${moduleDesc}数据", response = ResponseVO.class)
            @SysLog(value = "新增${moduleDesc}数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<${className}DTO>> save(@Validated @RequestBody ${className}VO param) {
    return ResponseUtils.ok("新增成功", ${LFClassName}Service.save(param));
    }

    /**
    * 修改${moduleDesc}数据
    *
    * @param param 修改对象DTO
    *
    * @return 修改结果
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改${moduleDesc}数据", notes = "修改${moduleDesc}数据", response = ResponseVO.class)
                @SysLog(value = "修改${moduleDesc}数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody ${className}VO param) {
    ${LFClassName}Service.update(param);
    return ResponseUtils.ok("修改成功");
    }

    /**
    * 删除${moduleDesc}数据
    *
    * @param id 对象唯一ID
    *
    * @return 删除成功或失败
    */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除${moduleDesc}数据", notes = "删除${moduleDesc}数据", response = ResponseVO.class)
                    @SysLog(value = "根据ID删除单条${moduleDesc}记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
    int i = ${LFClassName}Service.deleteById(id);
    if (i == 1) {
    return ResponseUtils.ok("删除成功:" + i);
    }
    return ResponseUtils.fail("删除失败" + i);
    }
                        /**
                        * 判断${moduleDesc}某个属性值是否存在
                        */
                        @PostMapping("/exist")
                        @ApiOperation(value = "判断${moduleDesc}某个属性值是否存在", notes = "判断${moduleDesc}某个属性值是否存在", response = ResponseVO.class)
                        @SysLog
                        public ResponseEntity<ResponseVO<Boolean>> isExisted(String prop, String value) {
                            if (null == prop || StrUtil.isEmpty(value)) {
                            return ResponseUtils.fail("参数错误,属性名称和值不能为空", null);
                            }
                            return ResponseUtils.ok("", ${LFClassName}Service.isExist(${className}Entity.class, prop, value));
                            }
    }


