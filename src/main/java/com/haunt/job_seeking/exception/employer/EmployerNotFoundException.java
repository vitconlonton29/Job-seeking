
package com.haunt.job_seeking.exception.employer;

import com.haunt.job_seeking.exception.base.NotFoundException;

public class EmployerNotFoundException extends NotFoundException {
  public EmployerNotFoundException(){
    setCode("com.haunt.job_seeking.exception.employer.EmployerNotFoundException");
  }

}