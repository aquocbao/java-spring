package com.example.bp.mapper;

import com.example.bp.model.dto.AssignedUserRoleDTO;
import com.example.bp.model.entity.AssignedUserRole;

public interface AssignedUserRoleMapper {
  AssignedUserRoleDTO toDto(AssignedUserRole entity);

  AssignedUserRole toEntity(AssignedUserRoleDTO dto);
}
