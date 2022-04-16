package com.example.bp.service.impl;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.bp.model.dto.UserDTO;
import com.example.bp.service.UserRoleService;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class UserRoleServiceImpl implements UserRoleService {

  @Override
  public UserDTO save(UserDTO model) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserDTO update(UserDTO model, Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserDTO findById(Long id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Long deleteById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }



}
