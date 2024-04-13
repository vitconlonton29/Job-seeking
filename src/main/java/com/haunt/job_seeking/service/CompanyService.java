package com.haunt.job_seeking.service;

import com.haunt.job_seeking.dto.request.company.CompanyRequest;
import com.haunt.job_seeking.dto.response.company.CompanyResponse;
import com.haunt.job_seeking.entity.Company;
import com.haunt.job_seeking.service.base.BaseService;

import java.util.List;

public interface CompanyService extends BaseService<Company> {
  CompanyResponse create(CompanyRequest request);

  CompanyResponse detail(String id);

  CompanyResponse update(String id, CompanyRequest request);

  List<CompanyResponse> list(int size, int page, boolean isAll, String keyword);

  CompanyResponse verify(String id);

  void delete(String id);
}