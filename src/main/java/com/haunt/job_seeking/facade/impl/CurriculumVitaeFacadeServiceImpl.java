package com.haunt.job_seeking.facade.impl;

import com.haunt.job_seeking.dto.request.curriculumvitae.CurriculumVitaeRequest;
import com.haunt.job_seeking.dto.request.curriculumvitaeattribute.CurriculumVitaeAttributeRequest;
import com.haunt.job_seeking.dto.response.curriculumvitae.CurriculumVitaeResponse;
import com.haunt.job_seeking.dto.response.curriculumvitaeattribute.CurriculumVitaeAttributeResponse;
import com.haunt.job_seeking.facade.CurriculumVitaeFacadeService;
import com.haunt.job_seeking.service.CurriculumVitaeAttributeService;
import com.haunt.job_seeking.service.CurriculumVitaeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurriculumVitaeFacadeServiceImpl implements CurriculumVitaeFacadeService {
  private final CurriculumVitaeService curriculumVitaeService;
  private final CurriculumVitaeAttributeService curriculumVitaeAttributeService;

  @Override
  public CurriculumVitaeResponse create(CurriculumVitaeRequest request) {
    log.info("(create) request:{}", request);

    CurriculumVitaeResponse cvResponse = curriculumVitaeService.create(request);
    List<CurriculumVitaeAttributeResponse> cvAttributes = new ArrayList<>();

    for (CurriculumVitaeAttributeRequest cvAttributeRequest : request.getAttributes()) {

      cvAttributeRequest.setCurriculumVitaeId(cvResponse.getId());
      cvAttributes.add(curriculumVitaeAttributeService.create(cvAttributeRequest));
    }

    return cvResponse;

  }

  @Override
  public void delete(String id) {
    log.info("(delete) id:{}", id);

    curriculumVitaeService.delete(id);
    curriculumVitaeAttributeService.deleteByCurriculumVitaeId(id);
  }

  public List<CurriculumVitaeResponse> list(int size, int page, boolean isAll) {
    log.info("(list) size:{}, page:{}, isAll:{}", size, page, isAll);

    return curriculumVitaeService.list(size, page, isAll);
  }

  public CurriculumVitaeResponse update(String id, CurriculumVitaeRequest request) {
    log.info("(update) request:{}", request);

    CurriculumVitaeResponse curriculumVitaeResponse = curriculumVitaeService.update(id, request);

    curriculumVitaeAttributeService.deleteByCurriculumVitaeId(id);

    List<CurriculumVitaeAttributeResponse> cvAttributes = new ArrayList<>();
    for (CurriculumVitaeAttributeRequest cvAttributeRequest : request.getAttributes()) {
      cvAttributeRequest.setCurriculumVitaeId(id);
      cvAttributes.add(curriculumVitaeAttributeService.create(cvAttributeRequest));

    }
    curriculumVitaeResponse.setAttributes(cvAttributes);

    return curriculumVitaeResponse;
  }

}
