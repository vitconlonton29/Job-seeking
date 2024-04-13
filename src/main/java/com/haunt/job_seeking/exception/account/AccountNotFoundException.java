package com.haunt.job_seeking.exception.account;

import com.haunt.job_seeking.exception.base.NotFoundException;

public class AccountNotFoundException extends NotFoundException {

  public AccountNotFoundException() {
    setCode("com.lawman.shop_sport.exception.account.AccountNotFoundException");
  }
}
