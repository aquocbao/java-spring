package com.example.bp.common.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ServerResponse {
  UNEXPECTED(100_000_000, "generic.unexpectedError", 500), CANNOT_PROCESS(100_000_002,
      "generic.unexpectedError", 404),
  /** validation error - bad request */
  ENTITY_INVALID(100_000_001, "generic.invalid", 400),
  /** user not found */
  USER_NOT_FOUND(101_000_001, "user.notFound", 404),
  /** No records found */
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
