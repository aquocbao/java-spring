package com.example.bp.model.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleDTO extends AbstractDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -904786520437355295L;

  private Long userRoleId;

  private Long isActive;

  private String roleName;

  List<AssignedUserRoleDTO> assignedUserRoles;
}