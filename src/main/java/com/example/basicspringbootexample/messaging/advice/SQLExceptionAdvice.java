package com.example.basicspringbootexample.messaging.advice;

import com.example.basicspringbootexample.messaging.exceptions.InvalidUserIdMessageIdReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SQLExceptionAdvice {

  @ExceptionHandler(InvalidUserIdMessageIdReference.class)
  public ResponseEntity<ErrorResponseWrapper<String>> handleInvalidUserIdMessageIdReference(
      InvalidUserIdMessageIdReference e) {
    ErrorResponseWrapper<String> respPayload;
    if (e.isClientFault()) {
      respPayload = new ErrorResponseWrapper<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    } else {
      respPayload = new ErrorResponseWrapper<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return ResponseEntity.status(respPayload.getHttpStatus()).body(respPayload);
  }
}
