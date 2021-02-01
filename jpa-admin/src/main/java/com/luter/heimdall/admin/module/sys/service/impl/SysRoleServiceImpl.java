package com.luter.heimdall.admin.module.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.dto.SysRoleDTO;
import com.luter.heimdall.admin.module.sys.entity.SysRoleEntity;
import com.luter.heimdall.admin.module.sys.entity.SysRoleResourceEntity;
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
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统角色 服务实现
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysRoleServiceImpl extends BaseServiceImpl implements SysRoleService {

    private final SysRoleRepository repository;
    private final SysRoleMapper mapper;

    private final SessionDAO sessionDAO;


    @Override
    public PageDTO<SysRoleDTO> list(SysRoleVO param, PagerVO pagerVO) {
        Page<SysRoleEntity> page = repository.findAll((Specification<SysRoleEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //            if (null != param.getId()) {
            //                predicates.add(criteriaBuilder.equal(root.get(SysRoleEntity_.ID), param.getId()));
            //            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    public SysRoleDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysRoleEntity.class, id));
    }

    @Override
    public SysRoleDTO save(SysRoleVO param) {
        SysRoleEntity toSave = new SysRoleEntity();
        //此处应该按需get set 数据
        //toSave.setXXX(param.getXXX());
        BeanUtil.copyProperties(param, toSave);
        final SysRoleEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override
    public void update(SysRoleVO param) {
        SysRoleEntity toUpdate = getEntityById(SysRoleEntity.class, param.getId());
        //toUpdate.setXXX(param.getXXX());
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
        if (null != param.getResources() && !param.getResources().isEmpty()) {
            final List<SysRoleResourceEntity> roleResourceEntities = param.getResources().stream().map(d -> {
                SysRoleResourceEntity entity = new SysRoleResourceEntity();
                entity.setRoleId(toUpdate.getId());
                entity.setResourceId(d.getId());
                return entity;
            }).collect(Collectors.toList());
            deleteByProperty(SysRoleResourceEntity.class, "roleId", toUpdate.getId());
            saveBatchEntity(roleResourceEntities);
            sessionDAO.clearAllUserAuthorities();
        }

    }
}
