package com.haunt.job_seeking.exception.base;


import static com.haunt.job_seeking.constants.JobSeekingConstants.StatusException.NOT_FOUND;

public class NotFoundException extends BaseException {
  public NotFoundException(String id, String objectName) {
    setCode("com.ncsgroup.profiling.exception.base.NotFoundException");
    setStatus(NOT_FOUND);
    addParam("id", id);
    addParam("objectName", objectName);
  }

  public NotFoundException() {
    setCode("com.ncsgroup.profiling.exception.base.NotFoundException");
    setStatus(NOT_FOUND);
  }
}
