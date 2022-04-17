package com.example.bp.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.bp.model.dto.AssignedUserRoleDTO;
import com.example.bp.service.AssignedUserRoleService;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class AssignedUserRoleServiceImpl implements AssignedUserRoleService {

  @Override
  public AssignedUserRoleDTO save(AssignedUserRoleDTO model) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AssignedUserRoleDTO update(AssignedUserRoleDTO model, Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AssignedUserRoleDTO findById(Long id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Long deleteById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<AssignedUserRoleDTO> findAll() {
    // TODO Auto-generated method stub
    return null;
  }



}
