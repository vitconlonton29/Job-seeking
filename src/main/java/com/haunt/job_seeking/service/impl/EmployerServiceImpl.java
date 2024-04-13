package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.dto.request.employer.EmployerRequest;
import com.haunt.job_seeking.dto.response.employer.EmployerResponse;
import com.haunt.job_seeking.entity.Employer;
import com.haunt.job_seeking.exception.employer.EmployerNotFoundException;
import com.haunt.job_seeking.repository.EmployerRepository;
import com.haunt.job_seeking.service.EmployerService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.haunt.job_seeking.utils.MapperUtils.*;

@Service
@Slf4j
public class EmployerServiceImpl extends BaseServiceImpl<Employer> implements EmployerService {
  private final EmployerRepository repository;

  public EmployerServiceImpl(EmployerRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public EmployerResponse create(EmployerRequest request, String id) {
    log.info("(create) request:{}", request);

    Employer employer = toEntity(request, Employer.class);
    employer.setId(id);

    return toDTO(create(employer), EmployerResponse.class);
  }

  @Override
  public void updateWithCompany(String employerId, String companyId) {
    log.info("(updateWithCompany) employerId: {}, companyId:{}", employerId, companyId);

    Employer employer = this.find(employerId);
    employer.setCompanyId(companyId);

    repository.save(employer);
  }

  @Override
  public EmployerResponse findById(String id) {
    log.info("(findById) id:{}", id);

    return toDTO(this.find(id), EmployerResponse.class);
  }

  private Employer find(String id) {
    log.info("(findById) id:{}", id);

    return repository.findById(id).orElseThrow(EmployerNotFoundException::new);
  }
}
