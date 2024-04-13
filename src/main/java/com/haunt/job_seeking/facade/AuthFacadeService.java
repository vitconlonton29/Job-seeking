package com.haunt.job_seeking.facade;

import com.haunt.job_seeking.dto.request.auth.RegistrationRequest;
import com.haunt.job_seeking.dto.response.auth.LoginResponse;
import com.haunt.job_seeking.dto.response.auth.RegistrationResponse;

public interface AuthFacadeService {
  LoginResponse login(String username, String password);

  RegistrationResponse register(RegistrationRequest request);

  String getCurrentUser();
}
