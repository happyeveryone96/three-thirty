package com.example.ThreeThirty_BE.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private ErrorCode errorCode;
  private String message;

  public CustomException(ErrorCode errorCode) {
    this.message = errorCode.getMessage();
    this.errorCode = errorCode;
  }
}
