package com.luter.heimdall.admin.module.sys.mapper;


import com.luter.heimdall.admin.module.sys.dto.SysRoleResourceDTO;
import com.luter.heimdall.admin.module.sys.entity.SysRoleResourceEntity;
import com.luter.heimdall.admin.module.sys.vo.SysRoleResourceVO;
import com.luter.heimdall.starter.model.mapper.BaseExtendMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 角色资源关系 对象映射
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysRoleResourceMapper extends BaseExtendMapper<SysRoleResourceDTO, SysRoleResourceEntity, SysRoleResourceVO> {
}
