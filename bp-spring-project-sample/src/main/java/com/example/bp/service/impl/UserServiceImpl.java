package com.example.bp.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bp.common.constant.BpResponse;
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
public class UserServiceImpl implements UserService, UserDetailsService {

  @Autowired
  UserMapper userMapper;

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public UserDTO save(UserDTO model) throws Exception {
    log.info("Saving new User: {} ", model.getUsername());
    User user = userMapper.toEntity(model);
    user.setPassword(passwordEncoder.encode(model.getPassword()));
    
    return userMapper.toDto(userRepository.save(user));
  }

  @Override
  public UserDTO update(UserDTO model, Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserDTO findById(Long id) throws Exception {
    log.debug("Find User by id : {}", id);
    User user = userRepository.findFirstByUserId(id);
    if (user != null) {
      return userMapper.toDto(user);
    }
    throw new ServerException(ServerResponse.USER_NOT_FOUND, id);

  }

  @Override
  public Long deleteById(Long id) {
    userRepository.deleteById(id);
    return id;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findFirstByUsername(username);
    if(user == null) {
      log.error("No User by username : {}", username);
      throw new ServerException(ServerResponse.USER_NOT_FOUND, username );
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getAssignedUserRoles().forEach(role  -> {
      authorities.add(new SimpleGrantedAuthority(role.getUserRole().getRoleName()));
    });
    
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
  }

  @Override
  public List<UserDTO> findAll() {
    return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public UserDTO findByUserName(String username) {
    User user = userRepository.findFirstByUsername(username);
    if (user != null) {
      return userMapper.toDto(user);
    }
    throw new ServerException(ServerResponse.USER_NOT_FOUND, username );
  }
  
}
