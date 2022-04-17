package com.example.bp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bp.common.constant.BpResponse;
import com.example.bp.common.constant.ServerResponse;
import com.example.bp.model.dto.UserDTO;
import com.example.bp.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @ApiOperation(value = "get user by id", notes = "get employee by id")
  @ApiResponses(
      value = {@ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN")})
  @GetMapping()
  public ResponseEntity<BpResponse> getUsers() {
    return new ResponseEntity<>(
        new BpResponse(ServerResponse.SUCCESS).setResult(userService.findAll()), HttpStatus.OK);
  }

  @ApiOperation(value = "get user by id", notes = "get employee by id")
  @ApiResponses(
      value = {@ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN")})
  @GetMapping("/{id}")
  public ResponseEntity<BpResponse> findById(@PathVariable Long id) {
    UserDTO user = null;
    try {
      user = userService.findById(id);
    } catch (Exception e) {
      return new ResponseEntity<>(new BpResponse(ServerResponse.USER_NOT_FOUND, id).setResult(user),
          HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(new BpResponse(ServerResponse.SUCCESS).setResult(user),
        HttpStatus.OK);
  }



}
