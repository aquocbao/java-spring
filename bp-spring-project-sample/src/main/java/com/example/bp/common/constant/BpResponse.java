package com.example.bp.common.constant;

import java.io.Serializable;
import com.example.bp.config.Translator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author bao.pham
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BpResponse implements Serializable {
  /**
  * 
  */
  private static final long serialVersionUID = 4359790903612064113L;

  private int status;
  @JsonIgnoreProperties(value = {"responseData", "hibernateLazyInitializer"})
  private Object data;
  private String message;

  public BpResponse(ServerResponse serverResponse, Object... params) {
    this.status = serverResponse.getHttpStatus();
    this.message = Translator.toLocale(serverResponse.getMessageCode(), params);
  }

  public BpResponse setResult(Object data) {
    this.data = data;
    return this;
  }

}
