package com.haunt.job_seeking.facade.impl;

import com.haunt.job_seeking.dto.request.job.JobRequest;
import com.haunt.job_seeking.dto.response.job.JobResponse;
import com.haunt.job_seeking.facade.AuthFacadeService;
import com.haunt.job_seeking.facade.JobFacadeService;
import com.haunt.job_seeking.service.EmployerService;
import com.haunt.job_seeking.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobFacadeServiceImpl implements JobFacadeService {
  private final JobService jobService;
  private final AuthFacadeService authFacadeService;
  private final EmployerService employerService;

  public JobResponse create(JobRequest request) {
    log.info("(create) request:{}", request);

    String userId = authFacadeService.getCurrentUser();

    String companyId = employerService.findById(userId).getCompanyId();

    request.setCompanyId(companyId);

    return jobService.create(request);
  }

  public List<JobResponse> list(int size, int page, boolean isAll, String keyword) {
    log.info("(list) size:{} page:{}  isAll:{},  keyword:{}", size, page, isAll, keyword);

    return jobService.list(size, page, isAll, keyword);
  }
  public void delete(String id) {
    log.info("(delete) id:{}", id);

    jobService.delete(id);
  }
  public JobResponse update(String id, JobRequest request) {
    log.info("(update) id:{} request:{}", id, request);

    setCompanyId(request);

    return jobService.update(id, request);
  }

  private void setCompanyId(JobRequest request) {
    log.info("(setCompanyId) request:{}", request);

    String userId = authFacadeService.getCurrentUser();
    String companyId = employerService.findById(userId).getCompanyId();
    request.setCompanyId(companyId);
  }
}
