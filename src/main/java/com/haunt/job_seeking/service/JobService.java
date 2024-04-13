package com.haunt.job_seeking.service;

import com.haunt.job_seeking.dto.request.job.JobRequest;
import com.haunt.job_seeking.dto.response.job.JobResponse;
import com.haunt.job_seeking.entity.Job;
import com.haunt.job_seeking.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService extends BaseService<Job> {
  JobResponse create(JobRequest request);
  List<JobResponse> list(int size, int page, boolean isAll, String keyword);
  void delete(String id);

  JobResponse update(String id, JobRequest request);
}
