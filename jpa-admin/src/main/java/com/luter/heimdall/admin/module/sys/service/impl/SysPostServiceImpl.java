package com.luter.heimdall.admin.module.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.dto.SysPostDTO;
import com.luter.heimdall.admin.module.sys.entity.SysPostEntity;
import com.luter.heimdall.admin.module.sys.mapper.SysPostMapper;
import com.luter.heimdall.admin.module.sys.repository.SysPostRepository;
import com.luter.heimdall.admin.module.sys.service.SysPostService;
import com.luter.heimdall.admin.module.sys.vo.SysPostVO;
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

/**
 * 岗位职责 服务实现
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysPostServiceImpl extends BaseServiceImpl implements SysPostService {

    private final SysPostRepository repository;
    private final SysPostMapper mapper;


    @Override
    public PageDTO<SysPostDTO> list(SysPostVO param, PagerVO pagerVO) {
        Page<SysPostEntity> page = repository.findAll((Specification<SysPostEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //            if (null != param.getId()) {
            //                predicates.add(criteriaBuilder.equal(root.get(SysPostEntity_.ID), param.getId()));
            //            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    public SysPostDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysPostEntity.class, id));
    }

    @Override
    public SysPostDTO save(SysPostVO param) {
        SysPostEntity toSave = new SysPostEntity();
        //此处应该按需get set 数据
        //toSave.setXXX(param.getXXX());
        BeanUtil.copyProperties(param, toSave);
        final SysPostEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override
    public void update(SysPostVO param) {
        SysPostEntity toUpdate = getEntityById(SysPostEntity.class, param.getId());
        toUpdate.setName(param.getName());
        toUpdate.setSeqNo(param.getSeqNo());
        updateEntity(toUpdate);
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysPostEntity.class, id);
    }
}
