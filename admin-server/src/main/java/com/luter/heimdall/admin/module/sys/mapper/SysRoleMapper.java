package com.luter.heimdall.admin.module.sys.mapper;


import com.luter.heimdall.admin.module.sys.dto.SysRoleDTO;
import com.luter.heimdall.admin.module.sys.entity.SysRoleEntity;
import com.luter.heimdall.admin.module.sys.vo.SysRoleVO;
import com.luter.heimdall.starter.model.mapper.BaseExtendMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysRoleMapper extends BaseExtendMapper<SysRoleDTO, SysRoleEntity, SysRoleVO> {
}
