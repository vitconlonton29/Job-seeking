package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.dto.request.jobapplication.JobApplicationRequest;
import com.haunt.job_seeking.dto.response.jobapplication.JobApplicationResponse;
import com.haunt.job_seeking.entity.JobApplication;
import com.haunt.job_seeking.repository.JobApplicationRepository;
import com.haunt.job_seeking.service.JobApplicationService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.haunt.job_seeking.utils.MapperUtils.*;


@Service
@Slf4j
public class JobApplicationServiceImpl extends BaseServiceImpl<JobApplication> implements JobApplicationService {

  public JobApplicationServiceImpl(JobApplicationRepository repository) {
    super(repository);
  }

  @Override
  public JobApplicationResponse create(JobApplicationRequest request) {
    log.info("(create) request:{}", request);

    JobApplication jobApplication = JobApplication.from(request);

    return toDTO(create(jobApplication), JobApplicationResponse.class);
  }
}
