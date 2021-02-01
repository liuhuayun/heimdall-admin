package com.luter.heimdall.admin.module.sys.repository;

import com.luter.heimdall.admin.module.sys.entity.SysResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 系统资源 Repository
 */
public interface SysResourceRepository extends JpaRepository<SysResourceEntity, Long>, JpaSpecificationExecutor<SysResourceEntity> {

    @Query(nativeQuery = true, value = "select * from m_resource where res_type =3 and uri <>'' and perm <>''")
    List<SysResourceEntity> loadSysPermissions();

    @Query(nativeQuery = true, value = "SELECT   " +
            "    *  " +
            " FROM  " +
            "    m_resource  " +
            " WHERE  " +
            "    m_resource.id IN (SELECT   " +
            "            m_role_resource.resource_id  " +
            "        FROM  " +
            "            m_role_resource  " +
            "        WHERE  " +
            "            m_role_resource.role_id IN (SELECT   " +
            "                    m_role_user.role_id  " +
            "                FROM  " +
            "                    m_role_user  " +
            "                WHERE  " +
            "                    m_role_user.user_id = ?1))  " +
            "        AND m_resource.res_type = 3  " +
            "        AND m_resource.uri <> ''  " +
            "        AND m_resource.perm <> ''  " +
            " ORDER BY m_resource.seq_no DESC")
    List<SysResourceEntity> loadUserPermissionsByUserId(Long id);

    @Query(nativeQuery = true, value = "SELECT   " +
            "    *  " +
            "FROM  " +
            "    m_resource  " +
            "WHERE  " +
            "    m_resource.id IN (SELECT   " +
            "            m_role_resource.resource_id  " +
            "        FROM  " +
            "            m_role_resource  " +
            "        WHERE  " +
            "            m_role_resource.role_id IN (SELECT   " +
            "                    m_role_user.role_id  " +
            "                FROM  " +
            "                    m_role_user  " +
            "                WHERE  " +
            "                    m_role_user.user_id = ?1))  " +
            "        AND m_resource.res_type <> 3  " +
            "ORDER BY m_resource.seq_no DESC")
    List<SysResourceEntity> loadUserMenusByUserId(Long id);

}
