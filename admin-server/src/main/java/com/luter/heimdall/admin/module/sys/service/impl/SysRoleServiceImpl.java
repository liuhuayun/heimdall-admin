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
import com.luter.heimdall.admin.module.sys.dto.SysRoleDTO;
import com.luter.heimdall.admin.module.sys.entity.SysResourceEntity;
import com.luter.heimdall.admin.module.sys.entity.SysRoleEntity;
import com.luter.heimdall.admin.module.sys.mapper.SysResourceMapper;
import com.luter.heimdall.admin.module.sys.mapper.SysRoleMapper;
import com.luter.heimdall.admin.module.sys.repository.SysRoleRepository;
import com.luter.heimdall.admin.module.sys.service.SysRoleService;
import com.luter.heimdall.admin.module.sys.vo.SysRoleVO;
import com.luter.heimdall.core.session.dao.SessionDAO;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysRoleServiceImpl extends BaseServiceImpl implements SysRoleService {
    private final SysRoleRepository repository;
    private final SysRoleMapper mapper;
    private final SysResourceMapper sysResourceMapper;


    private final SessionDAO sessionDAO;

    @Override
    @Cacheable(value = "roleList", sync = true)
    public PageDTO<SysRoleDTO> list(SysRoleVO param, PagerVO pagerVO) {
        Page<SysRoleEntity> page = repository.findAll((Specification<SysRoleEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    public List<SysRoleDTO> listAll() {
        final List<SysRoleEntity> sysRoleEntities = listAll(SysRoleEntity.class);
        return mapper.toDto(sysRoleEntities);
    }

    @Override
    public SysRoleDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysRoleEntity.class, id));
    }

    @Override
    public SysRoleDTO save(SysRoleVO param) {
        final SysRoleEntity toSave = mapper.voToEntity(param);
        final SysRoleEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override
    public void update(SysRoleVO param) {
        SysRoleEntity toUpdate = getEntityById(SysRoleEntity.class, param.getId());
        toUpdate.setTitle(param.getTitle());
        toUpdate.setRemarks(param.getRemarks());
        toUpdate.setDescription(param.getDescription());
//        repository.saveAndFlush(toUpdate);
        updateEntity(toUpdate);
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysRoleEntity.class, id);
    }

    @Override
    public void saveRoleAuth(SysRoleVO param) {
        SysRoleEntity toUpdate = getEntityById(SysRoleEntity.class, param.getId());
        if (null != param.getResources()) {
            final List<SysResourceEntity> sysResourceEntities = sysResourceMapper.voListToEntityList(param.getResources());
            Set<SysResourceEntity> ress = new HashSet<>(sysResourceEntities);
            toUpdate.setResources(ress);
            updateEntity(toUpdate);
            sessionDAO.clearAllUserAuthorities();
        }

    }
}
