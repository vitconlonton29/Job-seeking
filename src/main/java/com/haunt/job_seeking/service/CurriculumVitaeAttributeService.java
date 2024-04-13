package com.haunt.job_seeking.service;

import com.haunt.job_seeking.dto.request.curriculumvitaeattribute.CurriculumVitaeAttributeRequest;
import com.haunt.job_seeking.dto.response.curriculumvitaeattribute.CurriculumVitaeAttributeResponse;
import com.haunt.job_seeking.entity.CurriculumVitaeAttribute;
import com.haunt.job_seeking.service.base.BaseService;


public interface CurriculumVitaeAttributeService extends BaseService<CurriculumVitaeAttribute> {
  CurriculumVitaeAttributeResponse create(CurriculumVitaeAttributeRequest request);

  void deleteByCurriculumVitaeId(String id);
}
