package ${pkgPath}.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* ${moduleDesc} 对象映射
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${className}Mapper extends BaseExtendMapper<${className}DTO, ${className}Entity,${className}VO> {
}
