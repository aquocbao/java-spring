package com.example.bp.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.example.bp.common.constant.ServerResponse;
import com.example.bp.common.exception.ServerException;
import com.example.bp.common.util.CommonUtil;
import com.example.bp.mapper.UserMapper;
import com.example.bp.model.dto.AssignedUserRoleDTO;
import com.example.bp.model.dto.UserDTO;
import com.example.bp.model.entity.AssignedUserRole;
import com.example.bp.model.entity.User;
import com.example.bp.model.search.UserSearchCriteria;
import com.example.bp.model.search.UserSpecification;
import com.example.bp.repository.UserRepository;
import com.example.bp.repository.UserRoleRepository;
import com.example.bp.service.AssignedUserRoleService;
import com.example.bp.service.UserRoleService;
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
  
  @Autowired
  AssignedUserRoleService assignedUserRoleService;
  
  @Autowired
  UserRoleRepository userRoleRepository;

  @Override
  public UserDTO save(UserDTO model) throws Exception {
    log.info("Saving new User: {} ", model.getUsername());
    User entity = new User();
    userMapper.patch(model, entity);
    // set userId
    entity.setUserId(CommonUtil.generateID());
    // encode password
    entity.setPassword(passwordEncoder.encode(model.getPassword()));
    User user = userRepository.saveAndFlush(entity);
    // handle create assign user role
    if(!ObjectUtils.isEmpty(entity.getAssignedUserRoles())) {
      entity.getAssignedUserRoles().stream().forEach(assignedUserRole -> {
        assignedUserRole.setUser(user);
        assignedUserRole.setAssignedUserRoleId(CommonUtil.generateID());
        try {
          assignedUserRole.setUserRole(userRoleRepository.findById(assignedUserRole.getUserRole().getUserRoleId()).orElse(null));
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      });
      assignedUserRoleService.saveAll(model.getAssignedUserRoles());
    }
    return userMapper.toDto(user);
  }

  @Override
  public UserDTO update(UserDTO model, Long id) {
    log.debug("Update User by id : {}", id);
    User user = userRepository.findFirstByUserId(id);
    if (user != null) {
      userMapper.patch(model, user);
      return userMapper.toDto(userRepository.save(user));
    }
    throw new ServerException(ServerResponse.NO_RECORD_FOUND, id);
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
    if (user == null) {
      log.error("No User by username : {}", username);
      throw new ServerException(ServerResponse.USER_NOT_FOUND, username);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getAssignedUserRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getUserRole().getRoleName()));
    });

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), authorities);
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
    throw new ServerException(ServerResponse.USER_NOT_FOUND, username);
  }

  @Override
  public Page<UserDTO> search(UserSearchCriteria criteria, Pageable pageable) {
    Specification<User> campaignSpecification = UserSpecification.search(criteria);
    Page<UserDTO> crmTransferClientList = userRepository
        .findAll(campaignSpecification, criteria.pageRequest()).map(userMapper::toDto);
    return crmTransferClientList;
  }

}
