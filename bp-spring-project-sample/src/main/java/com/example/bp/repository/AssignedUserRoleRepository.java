package com.example.bp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.example.bp.model.entity.AssignedUserRole;

@Repository
public interface AssignedUserRoleRepository
    extends JpaRepository<AssignedUserRole, Long>, JpaSpecificationExecutor<AssignedUserRole> {

}
