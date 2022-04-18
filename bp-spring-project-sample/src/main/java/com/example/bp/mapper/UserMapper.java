package com.example.bp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import com.example.bp.model.dto.UserDTO;
import com.example.bp.model.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  UserDTO toDto(User entity);

  User toEntity(UserDTO dto);
  
  
  @InheritInverseConfiguration(name = "toDto")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  void patch(UserDTO dto, @MappingTarget User entity);
  
  @InheritInverseConfiguration(name = "toDto")
  @Mapping(target = "assignedUserRoles", ignore = true)
  void patchIgnoreAssignedUserRole(UserDTO dto, @MappingTarget User entity);
}
