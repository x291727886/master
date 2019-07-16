package com.xiajr.masterblog.modules.repository;

import com.xiajr.masterblog.modules.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    List<Role> findAllByStatus(int status);
}
