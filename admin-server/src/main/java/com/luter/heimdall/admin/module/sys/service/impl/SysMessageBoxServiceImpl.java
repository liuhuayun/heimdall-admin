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
import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.dto.SysMessageBoxDTO;
import com.luter.heimdall.admin.module.sys.entity.SysMessageBoxEntity;
import com.luter.heimdall.admin.module.sys.entity.SysMessageEntity;
import com.luter.heimdall.admin.module.sys.mapper.SysMessageBoxMapper;
import com.luter.heimdall.admin.module.sys.repository.SysMessageBoxRepository;
import com.luter.heimdall.admin.module.sys.service.SysMessageBoxService;
import com.luter.heimdall.admin.module.sys.vo.SysMessageBoxVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.utils.exception.LuterIllegalParameterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysMessageBoxServiceImpl extends BaseServiceImpl implements SysMessageBoxService {

    private final SysMessageBoxRepository repository;
    private final SysMessageBoxMapper mapper;


    @Override
    public PageDTO<SysMessageBoxDTO> list(SysMessageBoxVO param, PagerVO pagerVO) {
        Page<SysMessageBoxEntity> page = repository.findAll((Specification<SysMessageBoxEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //            if (null != param.getId()) {
            //                predicates.add(criteriaBuilder.equal(root.get(SysMessageBoxEntity_.ID), param.getId()));
            //            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    public SysMessageBoxDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysMessageBoxEntity.class, id));
    }

    @Override
    public SysMessageBoxDTO save(SysMessageBoxVO param) {
        final SysMessageEntity sysMessageEntity = getEntityById(SysMessageEntity.class, param.getMessageId());
        if (null == sysMessageEntity) {
            throw new LuterIllegalParameterException("数据错误，请检查");
        }
        String sql = "select count(*) from " + SysMessageBoxEntity.class.getAnnotation(Table.class).name()
                + " where receiver_id=?0 and message_id=?1";
        if (isExist(sql, param.getReceiverId(), param.getMessageId())) {
            return null;
        }
        SysMessageBoxEntity toSave = new SysMessageBoxEntity();
        BeanUtil.copyProperties(param, toSave);
        toSave.setReceivedTime(LocalDateTime.now());
        toSave.setStatus(1);
        toSave.setMessageId(sysMessageEntity.getId());
        toSave.setTitle(sysMessageEntity.getTitle());
        toSave.setMsgType(sysMessageEntity.getMsgType());
        final SysMessageBoxEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override
    public void update(SysMessageBoxVO param) {
        SysMessageBoxEntity toUpdate = getEntityById(SysMessageBoxEntity.class, param.getId());
        //toUpdate.setXXX(param.getXXX());
        updateEntity(toUpdate);
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysMessageBoxEntity.class, id);
    }
}
