/**
 * 
 */
package com.example.bp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.bp.common.constant.BpResponse;
import com.example.bp.model.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author bao.pham
 *
 */
public class UsersTest extends AbstractRestApiTest {

  private static final String REST_URL = "/users";

  @Test
  public void testRead() throws JsonMappingException, JsonProcessingException {
    HttpHeaders header = new HttpHeaders();
    header.add("Content-Type", "application/json");
    HttpEntity<User> entity = new HttpEntity<User>(header);
    ResponseEntity<BpResponse> response =
        this.restTemplate.exchange(REST_URL + "/1", HttpMethod.GET, entity, BpResponse.class);
    assertEquals(response.getStatusCode(), HttpStatus.OK);


  }

}
