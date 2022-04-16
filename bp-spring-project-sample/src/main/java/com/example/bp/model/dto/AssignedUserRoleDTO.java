package com.example.bp.model.dto;

import java.io.Serializable;
import com.example.bp.model.entity.User;
import com.example.bp.model.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignedUserRoleDTO extends AbstractDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3772312184925870036L;


  private long assignedUserRoleId;

  private Long isActive;

  private User user;

  private UserRole userRole;
}
