package com.example.basicspringbootexample.messaging.advice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponseWrapper<T> {
  String errorId;

  @JsonProperty("details")
  T clientSafePayload;

  @JsonIgnore HttpStatus httpStatus;

  public ErrorResponseWrapper(T clientSafePayload, HttpStatus httpStatus) {
    this.errorId = UUID.randomUUID().toString();
    this.clientSafePayload = clientSafePayload;
    this.httpStatus = httpStatus;
  }
}
