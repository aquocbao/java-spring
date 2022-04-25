package com.example.bp.service;

import java.util.List;
import com.example.bp.model.dto.AssignedUserRoleDTO;

public interface AssignedUserRoleService extends IService<AssignedUserRoleDTO> {
  
  List<AssignedUserRoleDTO> saveAll(List<AssignedUserRoleDTO> entities);

}
