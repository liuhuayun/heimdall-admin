package ${package.Entity?replace(".entity",".pmapper")};


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* ${cfg.classDesc!} 对象映射
*/
<#assign className = "${entity ?replace('Entity', '')}">
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${className}PojoMapper extends BaseExtendMapper<${className}DTO, ${className}Entity, ${className}VO> {
}
