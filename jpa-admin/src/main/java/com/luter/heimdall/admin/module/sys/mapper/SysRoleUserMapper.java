package com.luter.heimdall.admin.module.sys.mapper;


import com.luter.heimdall.admin.module.sys.dto.SysRoleUserDTO;
import com.luter.heimdall.admin.module.sys.entity.SysRoleUserEntity;
import com.luter.heimdall.admin.module.sys.vo.SysRoleUserVO;
import com.luter.heimdall.starter.model.mapper.BaseExtendMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 角色用户关系 对象映射
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysRoleUserMapper extends BaseExtendMapper<SysRoleUserDTO, SysRoleUserEntity, SysRoleUserVO> {
}
