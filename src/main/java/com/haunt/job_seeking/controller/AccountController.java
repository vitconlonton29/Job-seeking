package com.haunt.job_seeking.controller;

import com.haunt.job_seeking.dto.request.account.AccountRequest;
import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.account.AccountResponse;
import com.haunt.job_seeking.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
  private final AccountService accountService;

  @PostMapping
  public ResponseGeneral<AccountResponse> create(@RequestBody @Valid AccountRequest request) {
    log.info("(create) request: {}", request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          accountService.create(request)
    );
  }

}