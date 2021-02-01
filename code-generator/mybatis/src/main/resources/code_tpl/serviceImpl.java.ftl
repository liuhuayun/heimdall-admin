package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
<#assign cName>${entity?replace("Entity","")}</#assign>
<#assign dtoName>${entity ?replace('Entity', 'DTO')}</#assign>
<#assign voName>${entity ?replace('Entity', 'VO')}</#assign>
<#assign className = "${entity ?replace('Entity', '')}">
/**
 * <p>
 * ${cfg.classDesc!}${table.comment!}服务实现类
 * </p>
 *
 * @author ${author}
 */
@Service
@Slf4j
@RequiredArgsConstructor
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    private final ${className}PojoMapper ${className?uncap_first}PojoMapper;

    @Override
    public PageDTO<${dtoName}> list${cName}(${voName} param,  PagerVO pager) {
        QueryWrapper<${entity}> wrapper = new QueryWrapper<>();
    //       if (StrUtil.isNotEmpty(param.getName())) {
    //            wrapper.like("name", param.getName());
    //        }
        Page<${entity}> data = page(getPager(pager), wrapper);
        return toPageData(data, ${dtoName}.class);
    }

    @Override
    public ${dtoName} get${cName}ById(Long id) {
            return ${className?uncap_first}PojoMapper.toDto(get(id));
    }

    @Override
    public Boolean save${cName}( ${voName} param) {
            ${entity} entity = ${className?uncap_first}PojoMapper.voToEntity(param);
            return save(entity);
    }

    @Override

    public Boolean update${cName}( ${voName} param) {
            //根据ID查找要修改的实体
            ${entity} entity = get(param.getId());
            //修改数据
            //entity.setName(param.getName());
            return updateById(entity);
    }

    @Override
    public Boolean delete${cName}ById( Long id) {
            return removeById(id);
    }

}
</#if>
