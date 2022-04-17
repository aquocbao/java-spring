package com.example.bp.common.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * 
 * @author bao.pham
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ServerResponse {
  /** Success*/
  SUCCESS(001_000_000, "generic.sucessfully", 200),
  
  /** Unexpected */
  UNEXPECTED(100_000_000, "generic.unexpectedError", 500),
  
  /** Invalid */
  ENTITY_INVALID(100_000_001, "generic.invalid", 400),
  
  /** not found */
  USER_NOT_FOUND(101_000_001, "user.notFound", 404),
  NO_RECORD_FOUND(109_000_001, "common.noRecordFound", 404)
  ;
  

  private int code;
  private String messageCode;
  private int httpStatus;

  ServerResponse(int code, String messageCode, int httpStatus) {
    this.code = code;
    this.messageCode = messageCode;
    this.httpStatus = httpStatus;
  }

}
