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

import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.dto.SysMessageDTO;
import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.admin.module.sys.entity.SysMessageEntity;
import com.luter.heimdall.admin.module.sys.mapper.SysMessageMapper;
import com.luter.heimdall.admin.module.sys.repository.SysMessageRepository;
import com.luter.heimdall.admin.module.sys.service.SysMessageService;
import com.luter.heimdall.admin.module.sys.vo.SysMessageVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.utils.exception.LuterIllegalParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SysMessageServiceImpl extends BaseServiceImpl implements SysMessageService {

    private final SysMessageRepository repository;
    private final SysMessageMapper mapper;

    public SysMessageServiceImpl(SysMessageRepository repository, SysMessageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public PageDTO<SysMessageDTO> list(SysMessageVO param, PagerVO pagerVO) {
        Page<SysMessageEntity> page = repository.findAll((Specification<SysMessageEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //            if (null != param.getId()) {
            //                predicates.add(criteriaBuilder.equal(root.get(SysMessageEntity_.ID), param.getId()));
            //            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    public SysMessageDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysMessageEntity.class, id));
    }

    @Override
    public SysMessageDTO save(SysMessageVO param) {
        final SysUserDTO currentUser = getCurrentUserDTO(true, false);
        SysMessageEntity toSave = mapper.voToEntity(param);
        toSave.setSenderId(currentUser.getId());
        toSave.setSendTime(LocalDateTime.now());
        final SysMessageEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);

    }

    @Override
    public void update(SysMessageVO param) {
        SysMessageEntity toUpdate = getEntityById(SysMessageEntity.class, param.getId());
        //toUpdate.setXXX(param.getXXX());
        updateEntity(toUpdate);
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysMessageEntity.class, id);
    }
}
