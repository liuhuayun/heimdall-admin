package ${pkgPath}.repository;

import ${pkgPath}.entity.${className}Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
* ${moduleDesc} Repository
*/
public interface ${className}Repository extends JpaRepository<${className}Entity, Long>, JpaSpecificationExecutor<${className}Entity> {
    }
