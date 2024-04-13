package com.haunt.job_seeking.dto.request.account;

import com.haunt.job_seeking.dto.request.auth.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
  private String username;
  private String password;
  private String roleId;
}

