package com.example.ThreeThirty_BE.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException e) {
    ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
    return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
  }
}