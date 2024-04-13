package com.haunt.job_seeking.exception.base;

import static com.haunt.job_seeking.exception.base.StatusConstants.INTERNAL_SERVER_ERROR;

public class InternalServerError extends BaseException {
  public InternalServerError() {
    setCode("com.haunt.job_seeking.exception.base.InternalServerError");
    setStatus(INTERNAL_SERVER_ERROR);
  }

  public InternalServerError(String message) {
    setCode(message);
    setStatus(INTERNAL_SERVER_ERROR);
  }
}
