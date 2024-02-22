package com.project.ecommerce.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistsExcemption extends AuthenticationException {

  public UserAlreadyExistsExcemption(final String msg) {
    super(msg);
  }
}
