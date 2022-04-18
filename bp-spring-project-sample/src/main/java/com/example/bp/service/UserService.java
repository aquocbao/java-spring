package com.example.bp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.bp.model.dto.UserDTO;
import com.example.bp.model.search.UserSearchCriteria;

public interface UserService extends IService<UserDTO> {
  UserDTO findByUserName(String username);

  Page<UserDTO> search(UserSearchCriteria criteria, Pageable pageable);
}
