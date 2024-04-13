package com.haunt.job_seeking.exception.role;

import com.haunt.job_seeking.exception.base.BadRequestException;

public class RoleNameAlreadyExist extends BadRequestException {
  public RoleNameAlreadyExist() {
    setCode("com.haunt.job_seeking.exception.role.RoleNameAlreadyExist");
  }
}
