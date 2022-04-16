package com.example.bp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.example.bp.model.dto.UserDTO;
import com.example.bp.model.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  UserDTO toDto(User entity);

  User toEntity(UserDTO dto);
}
