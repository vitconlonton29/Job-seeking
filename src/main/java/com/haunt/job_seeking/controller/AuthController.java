package com.haunt.job_seeking.controller;

import com.haunt.job_seeking.dto.request.auth.LoginRequest;
import com.haunt.job_seeking.dto.request.auth.RegistrationRequest;
import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.auth.LoginResponse;
import com.haunt.job_seeking.dto.response.auth.RegistrationResponse;
import com.haunt.job_seeking.facade.AuthFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
  private final AuthFacadeService authFacadeService;

  @PostMapping("login")
  public ResponseGeneral<LoginResponse> login(@RequestBody LoginRequest request) {
    log.info("(login) request:{}", request);

    return ResponseGeneral.of(
          HttpStatus.OK.value(),
          SUCCESS,
          authFacadeService.login(request.getUsername(), request.getPassword())
    );
  }

  @PostMapping("register")
  public ResponseGeneral<RegistrationResponse> register(@RequestBody RegistrationRequest request) {
    log.info("(register) request:{}", request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          authFacadeService.register(request)
    );

  }

}
