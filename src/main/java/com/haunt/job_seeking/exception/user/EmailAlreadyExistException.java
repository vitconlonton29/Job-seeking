package com.haunt.job_seeking.exception.user;

import com.haunt.job_seeking.exception.base.BadRequestException;

public class EmailAlreadyExistException extends BadRequestException {
  public EmailAlreadyExistException() {
    setCode("com.haunt.job-seeking.exception.user.EmailAlreadyExistException");
  }
}
