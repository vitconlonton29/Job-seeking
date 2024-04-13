package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.dto.request.job.JobRequest;
import com.haunt.job_seeking.dto.response.job.JobResponse;
import com.haunt.job_seeking.entity.Job;
import com.haunt.job_seeking.exception.job.JobNotFoundException;
import com.haunt.job_seeking.repository.JobRepository;
import com.haunt.job_seeking.service.JobService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.haunt.job_seeking.utils.MapperUtils.*;

@Service
@Slf4j
public class JobServiceImpl extends BaseServiceImpl<Job> implements JobService {
  private final JobRepository repository;

  public JobServiceImpl(JobRepository repository) {
    super(repository);
    this.repository = repository;
  }

  public JobResponse create(JobRequest request) {
    log.info("(create) request:{}", request);

    Job job = Job.from(request);

    return toDTO(create(job), JobResponse.class);

  }

  public List<JobResponse> list(int size, int page, boolean isAll, String keyword) {
    log.info("(list) size:{} page:{}  isAll:{},  keyword:{}", size, page, isAll, keyword);

    List<Job> jobs = isAll ? list() : repository.search(keyword, PageRequest.of(page, size));

    return toDTOs(jobs, JobResponse.class);
  }
  @Override
  public void delete(String id) {
    log.info("(delete) id:{}", id);

    checkExist(id);
    delete(id);
  }


  public JobResponse update(String id, JobRequest request) {
    log.info("(update) id:{} request:{}", id, request);
    checkExist(id);

    Job jobExists = Job.from(request);
    jobExists.setId(id);

    return toDTO(update(jobExists), JobResponse.class);
  }

  private void checkExist(String id) {
    log.info("(checkExist) id:{}", id);

    if (get(id) == null) throw new JobNotFoundException();
  }
}
