package com.example.bp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.example.bp.model.entity.UserRole;

@Repository
public interface UserRoleRepository
    extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {

}
