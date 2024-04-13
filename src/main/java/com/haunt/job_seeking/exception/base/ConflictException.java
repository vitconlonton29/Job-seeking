package com.haunt.job_seeking.exception.base;


import static com.haunt.job_seeking.constants.JobSeekingConstants.StatusException.CONFLICT;

public class ConflictException extends BaseException {
  public ConflictException(String id, String objectName) {
    setStatus(CONFLICT);
    setCode("com.ncsgroup.profiling.exception.base.ConflictException");
    addParam("id", id);
    addParam("objectName", objectName);
  }

  public ConflictException(){
    setStatus(CONFLICT);
    setCode("com.ncsgroup.profiling.exception.base.ConflictException");
  }
}
