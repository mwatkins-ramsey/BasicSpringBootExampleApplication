package com.example.basicspringbootexample.messaging.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class InvalidUserIdMessageIdReference extends DataIntegrityViolationException {

  boolean isClientFault;//defaults to false

  public InvalidUserIdMessageIdReference(
      String message, DataIntegrityViolationException e, boolean clientFault) {
    super(message, e);
    this.isClientFault = clientFault;
  }

  public boolean isClientFault() {
    return isClientFault;
  }
}
