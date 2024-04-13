package com.haunt.job_seeking.dto.request.jobapplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationRequest {
  private String userId;
  private String cvId;
  private String jobId;
}
