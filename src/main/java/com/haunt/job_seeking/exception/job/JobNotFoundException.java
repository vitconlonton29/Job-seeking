package com.haunt.job_seeking.exception.job;

import com.haunt.job_seeking.exception.base.NotFoundException;

public class JobNotFoundException extends NotFoundException {
  public JobNotFoundException(){
    setCode("com.haunt.job_seeking.exception.job.JobNotFoundException");
  }
}
