package ${pkgPath}.service;

<#if baseService??>
    import ${baseService};
</#if>
import ${pkgPath}.dto.${className}DTO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
/**
* ${moduleDesc} 服务接口
*/
<#if baseService??>
    public interface ${className}Service extends ${baseServiceName} {
<#else>
    public interface ${className}Service {
</#if>


    /**
    * 列表查询
    *
    * @param param 查询参数VO
    *
    * @return DTO列表数据
    */
    PageDTO<${className}DTO> list(${className}VO param, PagerVO pagerVO);

    /**
    * 根据ID获取单个对象
    *
    * @param id 唯一ID
    *
    * @return 单个DTO对象
    */
    ${className}DTO getById(Long id);

    /**
    * 新增
    *
    * @param param 请求参数
    *
    * @return 新增成功后的DTO对象
    */
    ${className}DTO save(${className}VO param);

    /**
    * 修改
    *
    * @param param 请求参数
    *
    */
    void update(${className}VO param);

    /**
    * 删除
    *
    * @param id 唯一ID
    *
    * @return 删除数量，1成功，其他错误
    */
    int deleteById(Long id);


    }
