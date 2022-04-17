package com.example.bp.common.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.example.bp.common.constant.ServerResponse;
import com.example.bp.config.Translator;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author bao.pham
 *
 */
@Getter
@Setter
public class ServerException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 2667999585175015919L;
  private ServerResponse serverResponse;
  private int status;
  private String message;
  private List<Object> parameters = new ArrayList<Object>();

  public ServerException(ServerResponse serverResponse, Object... params) {
    this(null, serverResponse, params);
  }

  public ServerException(Throwable cause, ServerResponse serverResponse, Object... params) {
    super(serverResponse.getMessageCode(), cause);
    this.setServerResponse(serverResponse);
    this.setStatus(status);
    this.setMessage(Translator.toLocale(serverResponse.getMessageCode(), params));
    for (Object param : params) {
      this.parameters.add(param);
    }
  }

  public List<Object> getParameters() {
    return Collections.unmodifiableList(parameters);
  }

  public void addParameters(Object param) {
    this.parameters.add(param);
  }
}
