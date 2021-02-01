package com.luter.heimdall.admin.module.sys.mapper;


import com.luter.heimdall.admin.module.sys.dto.SysDictItemDTO;
import com.luter.heimdall.admin.module.sys.entity.SysDictItemEntity;
import com.luter.heimdall.admin.module.sys.vo.SysDictItemVO;
import com.luter.heimdall.starter.model.mapper.BaseExtendMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 字典项 对象映射
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysDictItemMapper extends BaseExtendMapper<SysDictItemDTO, SysDictItemEntity, SysDictItemVO> {
}
