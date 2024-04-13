package com.haunt.job_seeking.facade;

import com.haunt.job_seeking.dto.request.job.JobRequest;
import com.haunt.job_seeking.dto.response.job.JobResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobFacadeService {
  JobResponse create(JobRequest request);

  List<JobResponse> list(int size, int page, boolean isAll, String keyword);
  void delete(String id);
  JobResponse update(String id, JobRequest request);
}
