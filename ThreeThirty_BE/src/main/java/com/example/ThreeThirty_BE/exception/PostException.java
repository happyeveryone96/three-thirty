package com.example.ThreeThirty_BE.exception;

import lombok.Getter;

@Getter
public class PostException extends RuntimeException{
  private ErrorCode errorCode;
  private String message;

  public PostException(ErrorCode errorCode){
    this.message = errorCode.getMessage();
    this.errorCode = errorCode;
  }
}
