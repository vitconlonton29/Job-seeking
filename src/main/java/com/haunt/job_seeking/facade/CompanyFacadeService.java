package com.haunt.job_seeking.facade;

import com.haunt.job_seeking.dto.request.company.CompanyRequest;
import com.haunt.job_seeking.dto.response.company.CompanyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyFacadeService {
  CompanyResponse create(CompanyRequest request);

  CompanyResponse detail(String id);

  CompanyResponse update(String id, CompanyRequest request);

  List<CompanyResponse> list(int size, int page, boolean isAll, String keyword);

  CompanyResponse verify(String id);

  void delete(String id);
}
