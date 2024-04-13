package com.haunt.job_seeking.dto.response.jobapplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationResponse {
  private String id;
  private String userId;
  private String cvId;
  private String jobId;
}
