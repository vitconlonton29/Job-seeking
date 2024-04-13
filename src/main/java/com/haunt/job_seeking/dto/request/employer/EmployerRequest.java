package com.haunt.job_seeking.dto.request.employer;

import com.haunt.job_seeking.dto.request.auth.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerRequest {
  private String id;
  private String name;

  public static EmployerRequest from(RegistrationRequest request) {
    EmployerRequest employerRequest = new EmployerRequest();
    employerRequest.setId(request.getFullName());
    return employerRequest;
  }
}
