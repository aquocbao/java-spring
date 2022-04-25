/**
 * 
 */
package com.example.bp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bao.pham
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BpSpringProjectSampleApplication.class)
@ActiveProfiles("unittest")
@Transactional
public class AbstractRestApiTest {

  @Value("${server.port}")
  protected int port;

  /**
   * Returns the base url for your rest interface. *
   * 
   */
  protected String getBaseUrl() {
    return "http://localhost:" + port + "/";
  }

  protected String getUrl(String url) {
    return "http://localhost:" + port + "/" + url;
  }

  @Autowired
  protected TestRestTemplate restTemplate;
}
