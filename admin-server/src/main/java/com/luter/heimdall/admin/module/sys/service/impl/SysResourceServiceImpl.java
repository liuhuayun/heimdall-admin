package com.luter.heimdall.admin.module.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.luter.heimdall.admin.base.enums.SysResourceTypeEnum;
import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.dto.SysResourceDTO;
import com.luter.heimdall.admin.module.sys.entity.SysResourceEntity;
import com.luter.heimdall.admin.module.sys.entity.SysResourceEntity_;
import com.luter.heimdall.admin.module.sys.entity.SysRoleEntity;
import com.luter.heimdall.admin.module.sys.mapper.SysResourceMapper;
import com.luter.heimdall.admin.module.sys.repository.SysResourceRepository;
import com.luter.heimdall.admin.module.sys.service.SysResourceService;
import com.luter.heimdall.admin.module.sys.vo.SysResourceVO;
import com.luter.heimdall.core.authorization.dao.AuthorizationMetaDataCacheDao;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.utils.exception.LuterIllegalParameterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysResourceServiceImpl extends BaseServiceImpl implements SysResourceService {

    private final SysResourceRepository repository;
    private final SysResourceMapper mapper;
    private final AuthorizationMetaDataCacheDao authorizationMetaDataCacheDao;


    @Override
    public PageDTO<SysResourceDTO> list(SysResourceVO param, PagerVO pagerVO) {
        Page<SysResourceEntity> page = repository.findAll((Specification<SysResourceEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    public List<SysResourceEntity> listAll() {
        return listAll(SysResourceEntity.class);
    }

    @Override
    public SysResourceDTO getAllResourceTree(SysResourceVO param) {
        final List<SysResourceEntity> resourcesAll = repository.findAll((Specification<SysResourceEntity>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //如果是搜索
            if (param.getSearched()) {
                if (StrUtil.isNotEmpty(param.getTitle())) {
                    predicates.add(cb.like(root.get(SysResourceEntity_.TITLE), "%" + param.getTitle() + "%"));
                }
                if (null != param.getResType()) {
                    predicates.add(cb.equal(root.get(SysResourceEntity_.RES_TYPE), param.getResType()));
                }
                if (null != param.getEnabled()) {
                    predicates.add(cb.equal(root.get(SysResourceEntity_.ENABLED), param.getEnabled()));
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, Sort.by(Sort.Order.asc("seqNo")));
        //转DTO
        final List<SysResourceDTO> resources = mapper.toDto(resourcesAll);
        //根节点
        SysResourceDTO root = new SysResourceDTO();
        root.setId(0L);
        if (param.getSearched()) {
            //如果是查询返回平面数据
            return root.setChildren(resources);
        } else {
            //逐级找子节点,构造一棵树
            for (SysResourceDTO l : resources) {
                List<SysResourceDTO> children = resources.stream()
                        .filter(f -> l.getId().equals(f.getPid())).collect(Collectors.toList());
                l.setChildren(children);
            }
            return root.setChildren(resources.stream()
                    .filter(f -> null != f.getPid() && f.getPid().equals(root.getId())).collect(Collectors.toList()));
        }
    }

    @Override
    public SysResourceDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysResourceEntity.class, id));
    }

    @Override
    public SysResourceDTO save(SysResourceVO param) {
        SysResourceEntity toSave = new SysResourceEntity();
        //此处应该按需get set 数据
        //toSave.setXXX(param.getXXX());
        BeanUtil.copyProperties(param, toSave);
        final SysResourceEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override
    public void update(SysResourceVO param) {
        SysResourceEntity toUpdate = getEntityById(SysResourceEntity.class, param.getId());
        toUpdate.setTitle(param.getTitle());
        toUpdate.setRemarks(param.getRemarks());
        toUpdate.setIcon(param.getIcon());
        toUpdate.setKeepAlive(param.getKeepAlive());
        toUpdate.setPath(param.getPath());
        toUpdate.setComponent(param.getComponent());
        toUpdate.setSeqNo(param.getSeqNo());
        toUpdate.setAffix(param.getAffix());
        updateEntity(toUpdate);
        //如果是权限变更，则刷新过滤器链条
        if (SysResourceTypeEnum.PERM.value() == toUpdate.getResType()) {
            authorizationMetaDataCacheDao.clearSysAuthorities();
        }
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysResourceEntity.class, id);
    }

    @Override
    public Map<String, Object> getRoleAuthTree(Long roleId) {
        SysRoleEntity roleEntity = getEntityById(SysRoleEntity.class, roleId);
        if (null == roleEntity) {
            throw new LuterIllegalParameterException("角色未找到");
        }
        //角色已经具备的资源ID
        final List<Long> hasPermissionIds =
                roleEntity.getResources().stream().map(SysResourceEntity::getId).collect(Collectors.toList());
        //系统所有资源
        List<SysResourceEntity> resourcesAll = listAll(SysResourceEntity.class);
        //定义根节点
        SysResourceDTO root = new SysResourceDTO();
        root.setId(0L);
        root.setTitle("根节点");
        final List<SysResourceDTO> allNodes = mapper.toDto(resourcesAll);
        for (SysResourceDTO sysDepartmentDTO : allNodes) {
            List<SysResourceDTO> children = allNodes.stream()
                    .filter(f -> sysDepartmentDTO.getId().equals(f.getPid())).collect(Collectors.toList());
            sysDepartmentDTO.setChildren(children);
        }
        List<SysResourceDTO> rootNodes = allNodes.stream()
                .filter(f -> null != f.getPid() && f.getPid().equals(root.getId())).collect(Collectors.toList());
        Map<String, Object> data = Maps.newHashMap();
        //系统所有资源树结构
        data.put("tree", rootNodes);
        //此角色已经具备的资源
        data.put("checked", hasPermissionIds);
        return data;

    }


}
