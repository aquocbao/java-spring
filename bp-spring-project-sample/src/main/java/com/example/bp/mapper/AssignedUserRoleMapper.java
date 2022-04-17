package com.example.bp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.example.bp.model.dto.AssignedUserRoleDTO;
import com.example.bp.model.entity.AssignedUserRole;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssignedUserRoleMapper {
  AssignedUserRoleDTO toDto(AssignedUserRole entity);

  AssignedUserRole toEntity(AssignedUserRoleDTO dto);
}
