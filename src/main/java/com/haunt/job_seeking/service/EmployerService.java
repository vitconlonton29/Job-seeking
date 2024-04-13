package com.haunt.job_seeking.service;

import com.haunt.job_seeking.dto.request.employer.EmployerRequest;
import com.haunt.job_seeking.dto.response.employer.EmployerResponse;
import com.haunt.job_seeking.entity.Employer;
import com.haunt.job_seeking.service.base.BaseService;

public interface EmployerService extends BaseService<Employer> {
  EmployerResponse create(EmployerRequest request, String id);

  void updateWithCompany(String employerId, String companyId);

  EmployerResponse findById(String employeeId);
}
