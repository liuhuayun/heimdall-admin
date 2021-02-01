package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 系统角色 Repository
 */
public interface SysRoleRepository extends JpaRepository<SysRoleEntity, Long>, JpaSpecificationExecutor<SysRoleEntity> {

    @Query(nativeQuery = true, value = "SELECT  " +
            "    m_role.* " +
            "FROM " +
            "    m_role_user " +
            "        LEFT JOIN " +
            "    m_role ON m_role_user.role_id = m_role.id " +
            "        LEFT JOIN " +
            "    m_user ON m_role_user.user_id = m_user.id " +
            "WHERE " +
            "    m_user.id = ?1")
    List<SysRoleEntity> findUserRolesByUserId(Long id);
}
