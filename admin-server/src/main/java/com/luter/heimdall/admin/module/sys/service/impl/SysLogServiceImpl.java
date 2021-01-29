package com.luter.heimdall.admin.module.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.dto.SysLogDTO;
import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.admin.module.sys.entity.SysLogEntity;
import com.luter.heimdall.admin.module.sys.entity.SysLogEntity_;
import com.luter.heimdall.admin.module.sys.mapper.SysLogMapper;
import com.luter.heimdall.admin.module.sys.repository.SysLogRepository;
import com.luter.heimdall.admin.module.sys.service.SysLogService;
import com.luter.heimdall.admin.module.sys.vo.SysLogVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.utils.exception.LuterIllegalParameterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysLogServiceImpl extends BaseServiceImpl implements SysLogService {

    private final SysLogRepository repository;
    private final SysLogMapper mapper;


    @Override
    public PageDTO<SysLogDTO> list(SysLogVO param, PagerVO pagerVO) {
        final Page<SysLogEntity> page = repository.findAll((Specification<SysLogEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StrUtil.isNotEmpty(param.getUsername())) {
                predicates.add(criteriaBuilder.like(root.get(SysLogEntity_.USERNAME), "%" + param.getUsername() + "%"));
            }
            if (null != param.getRequestTimeStart() && null != param.getRequestTimeEnd()) {
                predicates.add(criteriaBuilder.between(
                        root.get(SysLogEntity_.requestTime), param.getRequestTimeStart(), param.getRequestTimeEnd()));
            }
            if (null != param.getStatus()) {
                predicates.add(criteriaBuilder.equal(root.get(SysLogEntity_.STATUS), param.getStatus()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    public SysLogDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysLogEntity.class, id));
    }

    @Override
    public SysLogDTO save(SysLogVO param) {
        SysLogEntity toSave = mapper.voToEntity(param);
        final SysUserDTO currentUser = getCurrentUserDTO(false, false);
        if (null != toSave && null != currentUser) {
            toSave.setUsername(currentUser.getUsername());
            toSave.setUserId(currentUser.getId() + "");
        }
        final SysLogEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override
    public void update(SysLogVO param) {
        SysLogEntity toUpdate = getEntityById(SysLogEntity.class, param.getId());
        //toUpdate.setXXX(param.getXXX());
        updateEntity(toUpdate);
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysLogEntity.class, id);
    }

    @Override
    public int deleteByBatch(List<Long> ids) {
        return deleteBatch(SysLogEntity.class, Lists.newArrayList(ids));
    }
}
