package com.example.bp.service.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bp.common.constant.ServerResponse;
import com.example.bp.common.exception.ServerException;
import com.example.bp.mapper.UserMapper;
import com.example.bp.model.dto.UserDTO;
import com.example.bp.model.entity.User;
import com.example.bp.repository.UserRepository;
import com.example.bp.service.UserService;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Autowired
  UserRepository userRepository;

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
    log.debug("Find User by id : {}", id);
    User user = userRepository.getById(id);
    if (user != null) {
      return userMapper.toDto(user);
    }

    throw new ServerException(ServerResponse.NO_RECORD_FOUND);

  }

  @Override
  public Long deleteById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

}
