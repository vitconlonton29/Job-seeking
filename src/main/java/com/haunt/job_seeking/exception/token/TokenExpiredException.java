package com.haunt.job_seeking.exception.token;

import com.haunt.job_seeking.exception.base.BadRequestException;

public class TokenExpiredException extends BadRequestException {
  public TokenExpiredException() {
    setCode("com..authentication.exception.token.TokenExpiredException");
  }
}
