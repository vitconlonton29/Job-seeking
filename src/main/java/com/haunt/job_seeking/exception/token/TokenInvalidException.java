package com.haunt.job_seeking.exception.token;

import com.haunt.job_seeking.exception.base.BadRequestException;

public class TokenInvalidException extends BadRequestException {
  public TokenInvalidException() {
    setCode("com.ncsgroup.core.authentication.exception.token.TokenInvalidException");
  }

}
