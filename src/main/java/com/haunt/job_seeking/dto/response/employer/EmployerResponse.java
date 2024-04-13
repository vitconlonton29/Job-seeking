package com.haunt.job_seeking.dto.response.employer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerResponse {
  private String id;
  private String name;
  private String companyId;
}
