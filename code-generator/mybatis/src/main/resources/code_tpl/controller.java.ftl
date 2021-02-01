package ${package.Controller};
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
<#assign cName>${entity?replace("Entity","")}</#assign>
<#assign dtoName>${entity ?replace('Entity', 'DTO')}</#assign>
<#assign voName>${entity ?replace('Entity', 'VO')}</#assign>
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>
/**
* <p>
    * ${cfg.classDesc!} 前端控制器
    * </p>
*
* @author ${author}
*/
@Slf4j
@Api(value = "${cfg.classDesc!}", tags = "${cfg.classDesc!}")
@RequiredArgsConstructor
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${entity?lower_case?replace(package.ModuleName,"")?replace("entity","")}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>
    private final  ${table.serviceName} ${table.serviceName?uncap_first};
    /**
    * ${cfg.classDesc!}列表分页排序查询
    */
    @GetMapping
    @SysLog(value = "列表数据查询", type = BizType.LIST)
    @ApiOperation(value = "列表数据查询", notes = "列表数据查询", response = ResponseVO.class)
    public ResponseEntity<ResponseVO<PageDTO<${dtoName}>>> list${cName}(${voName} param,PageVo page) {
        return ResponseUtils.ok(${table.serviceName?uncap_first}.list${cName}( param, page));
    }
    /**
    * 新增${cfg.classDesc!}
    */
    @PostMapping
    @ApiOperation(value = "新增", notes = "新增", response = ResponseVO.class)
    @SysLog(value = "新增", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<Void>> save${cName}(@RequestBody @Validated  ${voName} param) {
            if(${table.serviceName?uncap_first}.save${cName}(param)){
                    return ResponseUtils.ok("新增成功");
                }
            return  ResponseUtils.fail("新增失败");
    }
    /**
    * 修改${cfg.classDesc!}
    */
    @PutMapping
    @SysLog(value = "修改", type = BizType.UPDATE)
    @ApiOperation(value = "修改", notes = "修改", response = ResponseVO.class)
    public ResponseEntity<ResponseVO<Void>> update${cName}(@RequestBody  @Validated  ${voName} param) {
        if(${table.serviceName?uncap_first}.update${cName}(param)){
        return ResponseUtils.ok("修改成功");
        }
        return  ResponseUtils.fail("修改失败");
    }

    /**
    * 删除${cfg.classDesc!}
    */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除单条记录", notes = "根据ID删除单条记录", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete${cName}(@PathVariable Long id) {
            if (${table.serviceName?uncap_first}.delete${cName}ById(id)) {
            return ResponseUtils.ok("删除成功");
            }
            return  ResponseUtils.fail("删除失败");
    }
    /**
    * 获取${cfg.classDesc!}对象详情
    */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询单条记录详情", notes = "根据ID查询单条记录详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<${dtoName}>> get${cName}ById(@PathVariable Long id) {
    return ResponseUtils.ok(${table.serviceName?uncap_first}.get${cName}ById( id));
    }
    }
</#if>


