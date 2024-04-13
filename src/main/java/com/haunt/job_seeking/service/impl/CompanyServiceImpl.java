package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.dto.request.company.CompanyRequest;
import com.haunt.job_seeking.dto.response.company.CompanyResponse;
import com.haunt.job_seeking.entity.Company;
import com.haunt.job_seeking.exception.company.CompanyNotFoundException;
import com.haunt.job_seeking.repository.CompanyRepository;
import com.haunt.job_seeking.service.CompanyService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.haunt.job_seeking.utils.MapperUtils.toDTO;
import static com.haunt.job_seeking.utils.MapperUtils.toDTOs;

@Service
@Slf4j
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {

  private final CompanyRepository repository;

  public CompanyServiceImpl(CompanyRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public CompanyResponse create(CompanyRequest request) {
    log.info("(create) request:{}", request);

    Company company = Company.from(request);

    return toDTO(create(company), CompanyResponse.class);
  }

  @Override

  public CompanyResponse detail(String id) {
    log.info("(detail) id:{}", id);

    return toDTO(get(id), CompanyResponse.class);
  }


  public CompanyResponse update(String id, CompanyRequest request) {
    log.info("(update) id:{} request:{}", id, request);

    checkExist(id);
    Company companyExist = Company.from(request);
    companyExist.setId(id);

    return toDTO(update(companyExist), CompanyResponse.class);

  }

  @Override
  public List<CompanyResponse> list(int size, int page, boolean isAll, String keyword) {
    log.info("(list) size:{} page:{}  isAll:{},  keyword:{}", size, page, isAll, keyword);

    List<Company> companies = isAll ? list() : repository.search(keyword, PageRequest.of(page, size));

    return toDTOs(companies, CompanyResponse.class);
  }

  public CompanyResponse verify(String id) {
    log.info("(verify) id:{}", id);

    checkExist(id);
    Company companyExist = get(id);
    companyExist.setIs_verified(true);

    return toDTO(update(companyExist), CompanyResponse.class);
  }

  private void checkExist(String id) {
    log.info("(findById) id:{}", id);

    if (repository.findById(id) == null) throw new CompanyNotFoundException();

  }

  @Override
  public void delete(String id) {
    log.info("(delete) id:{}", id);

    Company company = get(id);
    company.setIsDeleted(true);

    repository.save(company);
  }

}