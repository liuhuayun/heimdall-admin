package com.luter.heimdall.admin.module.sys.mapper;


import com.luter.heimdall.admin.module.sys.dto.SysDictTypeDTO;
import com.luter.heimdall.admin.module.sys.entity.SysDictTypeEntity;
import com.luter.heimdall.admin.module.sys.vo.SysDictTypeVO;
import com.luter.heimdall.starter.model.mapper.BaseExtendMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysDictTypeMapper extends BaseExtendMapper<SysDictTypeDTO, SysDictTypeEntity, SysDictTypeVO> {
}
