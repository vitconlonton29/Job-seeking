package com.haunt.job_seeking.exception.base;


import static com.haunt.job_seeking.constants.JobSeekingConstants.StatusException.BAD_REQUEST;

public class BadRequestException extends BaseException {
  public BadRequestException() {
    setCode("com.ncsgroup.profiling.exception.base.BadRequestException");
    setStatus(BAD_REQUEST);
  }
}
