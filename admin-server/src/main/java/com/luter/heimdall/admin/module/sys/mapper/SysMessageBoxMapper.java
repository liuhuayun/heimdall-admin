package com.luter.heimdall.admin.module.sys.mapper;


import com.luter.heimdall.admin.module.sys.dto.SysMessageBoxDTO;
import com.luter.heimdall.admin.module.sys.entity.SysMessageBoxEntity;
import com.luter.heimdall.admin.module.sys.vo.SysMessageBoxVO;
import com.luter.heimdall.starter.model.mapper.BaseExtendMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysMessageBoxMapper extends BaseExtendMapper<SysMessageBoxDTO, SysMessageBoxEntity, SysMessageBoxVO> {
}
