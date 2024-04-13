package com.haunt.job_seeking.exception.curriculumvitae;

import com.haunt.job_seeking.exception.base.NotFoundException;

public class CurriculumVitaeNotFoundException extends NotFoundException {
  public CurriculumVitaeNotFoundException() {
    setCode("com.haunt.job_seeking.exception.company.CompanyNotFound");
  }
}
