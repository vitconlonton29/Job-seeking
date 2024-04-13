package com.haunt.job_seeking.service;

import com.haunt.job_seeking.dto.request.jobapplication.JobApplicationRequest;
import com.haunt.job_seeking.dto.response.jobapplication.JobApplicationResponse;
import com.haunt.job_seeking.entity.JobApplication;
import com.haunt.job_seeking.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public interface JobApplicationService extends BaseService<JobApplication> {
  JobApplicationResponse create(JobApplicationRequest request);
}
