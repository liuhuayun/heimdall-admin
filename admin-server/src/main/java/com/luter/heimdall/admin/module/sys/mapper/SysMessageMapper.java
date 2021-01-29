package com.luter.heimdall.admin.module.sys.mapper;


import com.luter.heimdall.admin.module.sys.dto.SysMessageDTO;
import com.luter.heimdall.admin.module.sys.entity.SysMessageEntity;
import com.luter.heimdall.admin.module.sys.vo.SysMessageVO;
import com.luter.heimdall.starter.model.mapper.BaseExtendMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysMessageMapper extends BaseExtendMapper<SysMessageDTO, SysMessageEntity, SysMessageVO> {
}
