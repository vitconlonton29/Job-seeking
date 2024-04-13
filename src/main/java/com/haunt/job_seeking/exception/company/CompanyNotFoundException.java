package com.haunt.job_seeking.exception.company;

import com.haunt.job_seeking.exception.base.NotFoundException;

public class CompanyNotFoundException extends NotFoundException {
  public CompanyNotFoundException() {
    setCode("com.haunt.job_seeking.exception.company.CompanyNotFound");
  }
}
