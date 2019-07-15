package com.xiajr.masterblog.modules.service;

import com.xiajr.masterblog.modules.entity.Permission;
import com.xiajr.masterblog.modules.entity.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author - langhsu
 * @create - 2018/5/18
 */
@Repository
public interface RolePermissionService {
    List<Permission> findPermissions(long roleId);
    void deleteByRoleId(long roleId);
    void add(Set<RolePermission> rolePermissions);

}
