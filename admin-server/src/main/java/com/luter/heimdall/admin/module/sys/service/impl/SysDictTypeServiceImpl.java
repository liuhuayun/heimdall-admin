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

import cn.hutool.core.util.StrUtil;

import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.dto.SysDictItemDTO;
import com.luter.heimdall.admin.module.sys.dto.SysDictTypeDTO;
import com.luter.heimdall.admin.module.sys.entity.SysDictItemEntity;
import com.luter.heimdall.admin.module.sys.entity.SysDictTypeEntity;
import com.luter.heimdall.admin.module.sys.entity.SysDictTypeEntity_;
import com.luter.heimdall.admin.module.sys.mapper.SysDictItemMapper;
import com.luter.heimdall.admin.module.sys.mapper.SysDictTypeMapper;
import com.luter.heimdall.admin.module.sys.repository.SysDictTypeRepository;
import com.luter.heimdall.admin.module.sys.service.SysDictTypeService;
import com.luter.heimdall.admin.module.sys.vo.SysDictTypeVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.utils.exception.LuterIllegalParameterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysDictTypeServiceImpl extends BaseServiceImpl implements SysDictTypeService {

    private final SysDictTypeRepository repository;
    private final SysDictTypeMapper mapper;
    private final SysDictItemMapper sysDictItemMapper;



    @Override
    public PageDTO<SysDictTypeDTO> list(SysDictTypeVO param, PagerVO pagerVO) {
        Page<SysDictTypeEntity> page = repository.findAll((Specification<SysDictTypeEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StrUtil.isNotEmpty(param.getName())) {
                predicates.add(criteriaBuilder.equal(root.get(SysDictTypeEntity_.NAME), param.getName()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    @Cacheable(value = "sysDict:allDictList", sync = true)
    public List<SysDictTypeDTO> listAll() {
        //查出所有的字典分类
        List<SysDictTypeEntity> list = listAll(SysDictTypeEntity.class);
        //查出所有的字典项分类
        final List<SysDictItemEntity> sysDictItemEntities = listAll(SysDictItemEntity.class);
        return list.stream().map(entity -> {
            SysDictTypeDTO dto = mapper.toDto(entity);
            //遍历查找每个字典分类下属的字典项
            final List<SysDictItemDTO> collect = sysDictItemEntities.stream()
                    .filter(item -> item.getTypeId().equals(entity.getId()))
                    .collect(Collectors.toList())
                    .stream().map(sysDictItemMapper::toDto).collect(Collectors.toList());
            //将字典项设置到字典分类的items中
            dto.setItems(collect);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public SysDictTypeDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysDictTypeEntity.class, id));
    }

    @Override
    public SysDictTypeDTO save(SysDictTypeVO param) {
        SysDictTypeEntity toSave = mapper.voToEntity(param);
        final SysDictTypeEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override
    public void update(SysDictTypeVO param) {
        SysDictTypeEntity toUpdate = getEntityById(SysDictTypeEntity.class, param.getId());
        //toUpdate.setXXX(param.getXXX());
        updateEntity(toUpdate);
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysDictTypeEntity.class, id);
    }
}
