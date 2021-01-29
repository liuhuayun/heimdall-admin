package com.luter.heimdall.admin.module.sys.mapper;


import com.luter.heimdall.admin.module.sys.dto.SysPostDTO;
import com.luter.heimdall.admin.module.sys.entity.SysPostEntity;
import com.luter.heimdall.admin.module.sys.vo.SysPostVO;
import com.luter.heimdall.starter.model.mapper.BaseExtendMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysPostMapper extends BaseExtendMapper<SysPostDTO, SysPostEntity, SysPostVO> {
}
