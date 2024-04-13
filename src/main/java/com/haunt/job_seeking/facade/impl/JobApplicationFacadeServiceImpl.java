package com.haunt.job_seeking.facade.impl;

import com.haunt.job_seeking.dto.request.jobapplication.JobApplicationRequest;
import com.haunt.job_seeking.dto.response.jobapplication.JobApplicationResponse;
import com.haunt.job_seeking.facade.AuthFacadeService;
import com.haunt.job_seeking.facade.JobApplicationFacadeService;
import com.haunt.job_seeking.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobApplicationFacadeServiceImpl implements JobApplicationFacadeService {
  private final JobApplicationService jobApplicationService;
  private final AuthFacadeService authFacadeService;

  public JobApplicationResponse apply(JobApplicationRequest request) {
    log.info("(apply) request:{}", request);

    String userId = authFacadeService.getCurrentUser();
    request.setUserId(userId);

    return jobApplicationService.create(request);
  }
}
