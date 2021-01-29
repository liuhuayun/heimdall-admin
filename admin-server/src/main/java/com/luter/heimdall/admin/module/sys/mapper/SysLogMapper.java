package com.luter.heimdall.admin.module.sys.mapper;


import com.luter.heimdall.admin.module.sys.dto.SysLogDTO;
import com.luter.heimdall.admin.module.sys.entity.SysLogEntity;
import com.luter.heimdall.admin.module.sys.vo.SysLogVO;
import com.luter.heimdall.starter.model.mapper.BaseExtendMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysLogMapper extends BaseExtendMapper<SysLogDTO, SysLogEntity, SysLogVO> {
}
