/*
 *
 *  *
 *  *
 *  *      Copyright 2020-2021 Luter.me
 *  *
 *  *      Licensed under the Apache License, Version 2.0 (the "License");
 *  *      you may not use this file except in compliance with the License.
 *  *      You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *      Unless required by applicable law or agreed to in writing, software
 *  *      distributed under the License is distributed on an "AS IS" BASIS,
 *  *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *      See the License for the specific language governing permissions and
 *  *      limitations under the License.
 *  *
 *  *
 *
 */

package com.luter.heimdall.admin.module.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.dto.SysDepartmentDTO;
import com.luter.heimdall.admin.module.sys.entity.SysDepartmentEntity;
import com.luter.heimdall.admin.module.sys.entity.SysDepartmentEntity_;
import com.luter.heimdall.admin.module.sys.mapper.SysDepartmentMapper;
import com.luter.heimdall.admin.module.sys.repository.SysDepartmentRepository;
import com.luter.heimdall.admin.module.sys.service.SysDepartmentService;
import com.luter.heimdall.admin.module.sys.vo.SysDepartmentVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.utils.exception.LuterIllegalParameterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysDepartmentServiceImpl extends BaseServiceImpl implements SysDepartmentService {

    private final SysDepartmentRepository repository;
    private final SysDepartmentMapper mapper;


    @Override
    public PageDTO<SysDepartmentDTO> list(SysDepartmentVO param, PagerVO pagerVO) {
        Page<SysDepartmentEntity> page = repository.findAll((Specification<SysDepartmentEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //            if (null != param.getId()) {
            //                predicates.add(criteriaBuilder.equal(root.get(SysDepartmentEntity_.ID), param.getId()));
            //            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    public SysDepartmentDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysDepartmentEntity.class, id));
    }

    @Override
    public SysDepartmentDTO save(SysDepartmentVO param) {
        SysDepartmentEntity toSave = new SysDepartmentEntity();
        //此处应该按需get set 数据
        //toSave.setXXX(param.getXXX());
        BeanUtil.copyProperties(param, toSave);
        final SysDepartmentEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override
    public void update(SysDepartmentVO param) {
        SysDepartmentEntity toUpdate = getEntityById(SysDepartmentEntity.class, param.getId());
        toUpdate.setRemarks(param.getRemarks());
        updateEntity(toUpdate);
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysDepartmentEntity.class, id);
    }

    @Override
    public SysDepartmentDTO getDepartmentTree(SysDepartmentVO param) {
        final List<SysDepartmentEntity> resourcesAll = repository.findAll((Specification<SysDepartmentEntity>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //如果是搜索
            if (param.getSearched()) {
                if (StrUtil.isNotEmpty(param.getName())) {
                    predicates.add(cb.like(root.get(SysDepartmentEntity_.NAME), "%" + param.getName() + "%"));
                }
                if (null != param.getEnabled()) {
                    predicates.add(cb.equal(root.get(SysDepartmentEntity_.ENABLED), param.getEnabled()));
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, Sort.by(Sort.Order.asc("seqNo")));
        final List<SysDepartmentDTO> depts = mapper.toDto(resourcesAll);
        SysDepartmentDTO root = new SysDepartmentDTO();
        root.setId(0L);
        if (param.getSearched()) {
            return root.setChildren(mapper.toDto(resourcesAll));
        } else {
            return root.setChildren(getTreeData(depts, root.getId()));
        }
    }

    private List<SysDepartmentDTO> getTreeData(List<SysDepartmentDTO> allNodes, Long rootId) {
        //找到根节点的直接下级
        List<SysDepartmentDTO> rootNodes = allNodes.stream().filter(f -> null != f.getPid() && f.getPid().equals(rootId)).collect(Collectors.toList());
        //逐级找子节点
        for (SysDepartmentDTO l : allNodes) {
            List<SysDepartmentDTO> children = allNodes.stream().filter(f -> l.getId().equals(f.getPid())).collect(Collectors.toList());
            l.setChildren(children);
        }
        //把根节点及其子节点全部返回
        return rootNodes;
    }
}
