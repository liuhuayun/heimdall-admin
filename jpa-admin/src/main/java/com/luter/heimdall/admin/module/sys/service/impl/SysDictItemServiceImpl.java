package com.luter.heimdall.admin.module.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.dto.SysDictItemDTO;
import com.luter.heimdall.admin.module.sys.entity.SysDictItemEntity;
import com.luter.heimdall.admin.module.sys.entity.SysDictTypeEntity;
import com.luter.heimdall.admin.module.sys.entity.SysDictTypeEntity_;
import com.luter.heimdall.admin.module.sys.mapper.SysDictItemMapper;
import com.luter.heimdall.admin.module.sys.repository.SysDictItemRepository;
import com.luter.heimdall.admin.module.sys.service.SysDictItemService;
import com.luter.heimdall.admin.module.sys.vo.SysDictItemVO;
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
 * 字典项 服务实现
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysDictItemServiceImpl extends BaseServiceImpl implements SysDictItemService {

    private final SysDictItemRepository repository;
    private final SysDictItemMapper mapper;


    @Override
    public PageDTO<SysDictItemDTO> list(SysDictItemVO param, PagerVO pagerVO) {
        Page<SysDictItemEntity> page = repository.findAll((Specification<SysDictItemEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //            if (null != param.getId()) {
            //                predicates.add(criteriaBuilder.equal(root.get(SysDictItemEntity_.ID), param.getId()));
            //            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    public SysDictItemDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysDictItemEntity.class, id));
    }

    @Override
    public SysDictItemDTO save(SysDictItemVO param) {
        SysDictItemEntity toSave = new SysDictItemEntity();
        //此处应该按需get set 数据
        //toSave.setXXX(param.getXXX());
        BeanUtil.copyProperties(param, toSave);
        final SysDictItemEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override
    public void update(SysDictItemVO param) {
        SysDictItemEntity toUpdate = getEntityById(SysDictItemEntity.class, param.getId());
        //toUpdate.setXXX(param.getXXX());
        updateEntity(toUpdate);
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysDictItemEntity.class, id);
    }

    @Override
    public List<SysDictItemDTO> listByTypeId(Long typeId) {
        if (null == typeId) {
            throw new LuterIllegalParameterException("请提供字典分类ID");
        }
        String hql = " from SysDictItemEntity where typeId = ?0 order by seqNo desc";
        return mapper.toDto(listByHql(hql, typeId));
    }

    @Override
    public List<SysDictItemDTO> listByTypeName(String typeName) {
        if (StrUtil.isEmpty(typeName)) {
            throw new LuterIllegalParameterException("请提供字典名称");
        }
        final SysDictTypeEntity dictTypeEntity = findUniqueEntityByProperty(SysDictTypeEntity.class, SysDictTypeEntity_.NAME, typeName);
        if (null == dictTypeEntity) {
            throw new LuterIllegalParameterException("字典名称错误:" + typeName);
        }
        return listByTypeId(dictTypeEntity.getId());
    }
}
