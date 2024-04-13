package com.haunt.job_seeking.controller;

import com.haunt.job_seeking.dto.request.jobapplication.JobApplicationRequest;
import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.jobapplication.JobApplicationResponse;
import com.haunt.job_seeking.facade.JobApplicationFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@RestController
@RequestMapping("api/v1/job-application")
@RequiredArgsConstructor
@Slf4j
public class JobApplicationController {
  private final JobApplicationFacadeService jobApplicationFacadeService;

  @PostMapping
  public ResponseGeneral<JobApplicationResponse> apply(@RequestBody JobApplicationRequest request) {
    log.info("(apply) request:{}", request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          jobApplicationFacadeService.apply(request)
    );
  }
}
