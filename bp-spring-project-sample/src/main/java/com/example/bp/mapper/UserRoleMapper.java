package com.example.bp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.example.bp.model.dto.UserRoleDTO;
import com.example.bp.model.entity.UserRole;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {

  UserRoleDTO toDto(UserRole entity);

  UserRole toEntity(UserRoleDTO dto);
}
