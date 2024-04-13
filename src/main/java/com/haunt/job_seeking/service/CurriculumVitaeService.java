package com.haunt.job_seeking.service;

import com.haunt.job_seeking.dto.request.curriculumvitae.CurriculumVitaeRequest;
import com.haunt.job_seeking.dto.response.curriculumvitae.CurriculumVitaeResponse;
import com.haunt.job_seeking.entity.CurriculumVitae;
import com.haunt.job_seeking.service.base.BaseService;

import java.util.List;


public interface CurriculumVitaeService extends BaseService<CurriculumVitae> {
  CurriculumVitaeResponse create(CurriculumVitaeRequest request);

  List<CurriculumVitaeResponse> list(int size, int page, boolean isAll);
  CurriculumVitaeResponse update(String id, CurriculumVitaeRequest request);


}
