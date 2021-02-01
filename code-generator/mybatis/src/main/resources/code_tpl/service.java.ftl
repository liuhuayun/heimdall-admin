package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

<#assign cName>${entity?replace("Entity","")}</#assign>
<#assign dtoName>${entity ?replace('Entity', 'DTO')}</#assign>
<#assign voName>${entity ?replace('Entity', 'VO')}</#assign>
/**
* <p>
    * ${cfg.classDesc!}${table.comment!}服务类
    * </p>
*
* @author ${author}
*/
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * 分页排序条件查询
    *
    *
    * @param param   查询参数
    * @param pager   分页对象
    * @return the response entity
    */
    PageDTO<${dtoName}> list${cName}( ${voName} param, PagerVO pager);
    /**
    * 根据ID获取单个对象
    *
    *
    * @param id   唯一ID
    * @return DTO对象
    */
    ${dtoName} get${cName}ById( Long id);

    /**
    * 新增
    *
    *
    * @param param   请求参数
    * @return 成功失败结果
    */
    Boolean save${cName}( ${voName} param);

    /**
    * 修改
    *
    *
    * @param param   请求参数
    * @return 成功失败结果
    */
    Boolean update${cName}( ${voName} param);

    /**
    * 删除
    *
    *
    * @param id   唯一ID
    * @return 成功失败结果
    */
    Boolean delete${cName}ById( Long id);

    }
</#if>
