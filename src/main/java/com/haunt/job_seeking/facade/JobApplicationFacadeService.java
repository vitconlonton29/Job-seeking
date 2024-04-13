package com.haunt.job_seeking.facade;

import com.haunt.job_seeking.dto.request.jobapplication.JobApplicationRequest;
import com.haunt.job_seeking.dto.response.jobapplication.JobApplicationResponse;
import org.springframework.stereotype.Service;

@Service
public interface JobApplicationFacadeService {
  JobApplicationResponse apply(JobApplicationRequest request);
}
