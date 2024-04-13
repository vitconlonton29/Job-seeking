package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.dto.request.curriculumvitaeattribute.CurriculumVitaeAttributeRequest;
import com.haunt.job_seeking.dto.response.curriculumvitaeattribute.CurriculumVitaeAttributeResponse;
import com.haunt.job_seeking.entity.CurriculumVitaeAttribute;
import com.haunt.job_seeking.repository.CurriculumVitaeAttributeRepository;
import com.haunt.job_seeking.service.CurriculumVitaeAttributeService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.haunt.job_seeking.utils.MapperUtils.toDTO;

@Service
@Slf4j
public class CurriculumVitaeAttributeServiceImpl extends BaseServiceImpl<CurriculumVitaeAttribute> implements CurriculumVitaeAttributeService {
  private final CurriculumVitaeAttributeRepository repository;

  public CurriculumVitaeAttributeServiceImpl(CurriculumVitaeAttributeRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public CurriculumVitaeAttributeResponse create(CurriculumVitaeAttributeRequest request) {
    log.info("(create) request:{}", request);

    CurriculumVitaeAttribute cva = CurriculumVitaeAttribute.from(request);

    return toDTO(create(cva), CurriculumVitaeAttributeResponse.class);
  }

  @Override
  public void deleteByCurriculumVitaeId(String cvId) {
    log.info("(deleteByCurriculumVitaeId) id:{}", cvId);

    repository.deleteByCurriculumVitaeId(cvId);

  }


}
