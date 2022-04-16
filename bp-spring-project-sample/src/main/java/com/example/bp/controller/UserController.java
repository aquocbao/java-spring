package com.example.bp.controller;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bp.model.dto.UserDTO;
import com.example.bp.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.models.Response;

@RestController
@RequestMapping("user")
public class UserController {
  
  @Autowired
  UserService userService;
  
  @ApiOperation(value = "get user by id", notes = "get employee by id")
  @ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
      UserDTO user = null;
      try {
          user = userService.findById(id);
      } catch (Exception e) {
          e.printStackTrace();
          return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
  }

}
