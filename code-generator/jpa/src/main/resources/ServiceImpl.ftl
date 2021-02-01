package ${pkgPath}.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
<#if baseServiceImpl??>
    import ${baseServiceImpl};
</#if>
import ${pkgPath}.dto.${className}DTO;
import ${pkgPath}.entity.${className}Entity;
import ${pkgPath}.repository.${className}Repository;
import ${pkgPath}.service.${className}Service;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
/**
* ${moduleDesc} 服务实现
*/
@Service
@Slf4j
@RequiredArgsConstructor
<#if baseServiceImpl??>
    public class ${className}ServiceImpl extends ${baseServiceImplName} implements ${className}Service {
<#else>
    public class ${className}ServiceImpl  implements ${className}Service {
</#if>

    private final ${className}Repository repository;
    private final ${className}Mapper mapper;




                    @Override
                public PageDTO<${className}DTO> list(${className}VO param,  PagerVO pagerVO) {
                     Page<${className}Entity> page = repository.findAll((Specification<${className}Entity>) (root, query, criteriaBuilder) -> {
                         List<Predicate> predicates = new ArrayList<>();
                //            if (null != param.getId()) {
                //                predicates.add(criteriaBuilder.equal(root.get(${className}Entity_.ID), param.getId()));
                //            }
                        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }, getPager(pagerVO));
                return toPageData(page.map(mapper::toDto));
                }

                    @Override
                    public ${className}DTO getById(Long id) {
                        return mapper.toDto(getEntityById(${className}Entity.class, id));
                    }

                    @Override
                    public ${className}DTO save(${className}VO param) {
                    ${className}Entity toSave = new ${className}Entity();
                    //此处应该按需get set 数据
                    //toSave.setXXX(param.getXXX());
                    BeanUtil.copyProperties(param, toSave);
                    final ${className}Entity entity = repository.saveAndFlush(toSave);
                    return mapper.toDto(entity);
                    }

                @Override
                public void update(${className}VO param) {
                ${className}Entity toUpdate = getEntityById(${className}Entity.class, param.getId());
                    //toUpdate.setXXX(param.getXXX());
                    updateEntity(toUpdate);
                }

                @Override
                public int deleteById(Long id) {
                if (null == id) {
                    throw new LuterIllegalParameterException("ID不能为空");
                }
                    return deleteEntityById(${className}Entity.class, id);
                }
                }
