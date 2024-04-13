package com.haunt.job_seeking.exception.auth;

import com.haunt.job_seeking.exception.base.BadRequestException;

public class PasswordIncorrectException extends BadRequestException {
  public PasswordIncorrectException() {
    setCode("exception.authentication.PasswordIncorrectException");
  }
}
