package com.haunt.job_seeking.exception.user;

import com.haunt.job_seeking.exception.base.BadRequestException;

public class PhoneNumberAlreadyExistException extends BadRequestException {
  public PhoneNumberAlreadyExistException() {
    setCode("com.haunt.job-seeking.exception.user.PhoneNumberAlreadyExistException");
  }
}
