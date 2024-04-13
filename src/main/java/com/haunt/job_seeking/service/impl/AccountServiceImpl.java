package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.dto.request.account.AccountRequest;
import com.haunt.job_seeking.dto.response.account.AccountResponse;
import com.haunt.job_seeking.entity.Account;
import com.haunt.job_seeking.exception.account.AccountNotFoundException;
import com.haunt.job_seeking.exception.account.UsernameAlreadyExist;
import com.haunt.job_seeking.exception.auth.PasswordIncorrectException;
import com.haunt.job_seeking.repository.AccountRepository;
import com.haunt.job_seeking.service.AccountService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.haunt.job_seeking.utils.MapperUtils.*;
import static com.haunt.job_seeking.constants.JobSeekingConstants.AuthConstant.*;
@Service
@Slf4j
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

  private final AccountRepository repository;
  private final PasswordEncoder passwordEncoder;

  public AccountServiceImpl(AccountRepository repository, PasswordEncoder passwordEncoder) {
    super(repository);
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public AccountResponse create(AccountRequest request) {
    log.info("(create) request: {}", request);

    checkExistedByUsername(request.getUsername());

    Account account = Account.from(request);
    account.setPassword(passwordEncoder.encode(request.getPassword()));
    account.setIsActive(ENABLED);

    return toDTO(create(account), AccountResponse.class);
  }


  public void equalPassword(String passwordRaw, String passwordEncrypted) {
    log.info("(equalPassword) raw:{} encrypted:{}", passwordRaw, passwordEncrypted);

    if (!passwordEncoder.matches(passwordRaw, passwordEncrypted)) {
      throw new PasswordIncorrectException();
    }
  }

  @Override
  public Account findByUsername(String username) {
    log.info("(findByUsername) username:{}", username);

    return repository.findByUsername(username).orElseThrow(AccountNotFoundException::new);
  }

  private void checkExistedByUsername(String username) {
    log.info("(checkExistedByUsername) username:{}", username);

    if (repository.existsByUsername(username)) {
      log.warn("(checkExistedByUsername) Username already exists: {}", username);
      throw new UsernameAlreadyExist();
    }
  }
}
