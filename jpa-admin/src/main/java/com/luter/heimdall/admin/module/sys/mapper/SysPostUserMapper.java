package com.luter.heimdall.admin.module.sys.mapper;


import com.luter.heimdall.admin.module.sys.dto.SysPostUserDTO;
import com.luter.heimdall.admin.module.sys.entity.SysPostUserEntity;
import com.luter.heimdall.admin.module.sys.vo.SysPostUserVO;
import com.luter.heimdall.starter.model.mapper.BaseExtendMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 岗位用户关系 对象映射
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysPostUserMapper extends BaseExtendMapper<SysPostUserDTO, SysPostUserEntity, SysPostUserVO> {
}
