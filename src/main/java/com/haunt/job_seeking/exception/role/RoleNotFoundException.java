package com.haunt.job_seeking.exception.role;

import com.haunt.job_seeking.exception.base.NotFoundException;

public class RoleNotFoundException extends NotFoundException {
  public RoleNotFoundException(){
    setCode("com.haunt.job_seeking.exception.role.RoleNotFoundException");
  }
}
