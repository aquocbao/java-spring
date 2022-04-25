/**
 * 
 */
package com.example.bp;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bp.model.dto.AssignedUserRoleDTO;
import com.example.bp.model.dto.UserDTO;
import com.example.bp.model.dto.UserRoleDTO;
import com.example.bp.service.UserService;

/**
 * @author bao.pham
 *
 */
public class UsersTest extends AbstractRestApiTest {

  private static final String REST_URL = "/users";

  @Autowired
  UserService userService;

  @Test
  public void testReadAll() throws Exception {
    List<UserDTO> user = userService.findAll();
    assertTrue(!user.isEmpty());
  }
  
  @Test
  public void testReadById() throws Exception {
    UserDTO user = userService.findById(1L);
    assertEquals(user.getUsername(), "username01");
  }
  
  @Test
  public void testCreate() throws Exception {
    UserDTO entity = new UserDTO();
    entity.setUsername("username04");
    entity.setFirstName("Pham");
    entity.setLastName("B");
    entity.setPassword("123456");
    AssignedUserRoleDTO assignedUserRoleDTO = new AssignedUserRoleDTO();
    UserRoleDTO role = new UserRoleDTO();
    new UserRoleDTO().setUserRoleId(1L);;
    assignedUserRoleDTO.setUserRole(role);
    UserDTO user = userService.save(entity);
    assertEquals(user.getUsername(), "username04");
  }
  
  @Test
  public void testReadByUsername() throws Exception {
    UserDTO user = userService.findByUserName("username03");
    assertEquals(user.getUsername(), "username03");
  }


}
