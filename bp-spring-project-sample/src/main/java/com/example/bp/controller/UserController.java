package com.example.bp.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bp.common.constant.BpResponse;
import com.example.bp.common.constant.ServerResponse;
import com.example.bp.model.dto.UserDTO;
import com.example.bp.model.search.UserSearchCriteria;
import com.example.bp.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController {

  @Autowired
  UserService userService;

  @ApiOperation(value = "Get All Users", notes = "Get All Users")
  @ApiResponses(
      value = {@ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN")})
  @GetMapping()
  public ResponseEntity<BpResponse> getUsers() {
    return new ResponseEntity<>(
        new BpResponse(ServerResponse.SUCCESS).setResult(userService.findAll()), HttpStatus.OK);
  }

  @ApiOperation(value = "Get user by id", notes = "Get employee by id")
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

  @ApiOperation(value = "Create new User", notes = "Create new User")
  @ApiResponses(
      value = {@ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN")})
  @PostMapping()
  public ResponseEntity<BpResponse> create(@RequestBody UserDTO model, Principal principal) {
    try {
      // add create by, updated by
      model.setCreatedBy(getPrincipalName(principal));
      model.setUpdatedBy(getPrincipalName(principal));
      UserDTO user =  userService.save(model);
      return new ResponseEntity<>(
          new BpResponse(ServerResponse.SUCCESS).setResult(user), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(new BpResponse(ServerResponse.UNEXPECTED), HttpStatus.FORBIDDEN);
    }
  }

  @ApiOperation(value = "Search all user by page", notes = "Search all user by page")
  @ApiResponses(
      value = {@ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN")})
  @PostMapping("/search")
  public ResponseEntity<BpResponse> search(@RequestBody UserSearchCriteria userSearchCriteria) {
    return new ResponseEntity<>(
        new BpResponse(ServerResponse.SUCCESS)
            .setResult(userService.search(userSearchCriteria, userSearchCriteria.pageRequest())),
        HttpStatus.OK);
  }

}
