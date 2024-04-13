package com.haunt.job_seeking.service;

import com.haunt.job_seeking.dto.request.account.AccountRequest;
import com.haunt.job_seeking.dto.response.account.AccountResponse;
import com.haunt.job_seeking.entity.Account;
import com.haunt.job_seeking.service.base.BaseService;

public interface AccountService extends BaseService<Account> {
  AccountResponse create(AccountRequest request);

  Account findByUsername(String username);

  void equalPassword(String passwordRaw, String passwordEncrypted);
}
