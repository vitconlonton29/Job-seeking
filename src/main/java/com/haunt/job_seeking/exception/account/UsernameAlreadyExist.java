package com.haunt.job_seeking.exception.account;

import com.haunt.job_seeking.exception.base.BadRequestException;

public class UsernameAlreadyExist extends BadRequestException {
  public UsernameAlreadyExist() {
    setCode("com.haunt.job_seeking.exception.account.UsernameAlreadyExist");
  }
}
