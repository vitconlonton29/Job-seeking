package com.haunt.job_seeking.facade;

import com.haunt.job_seeking.dto.request.curriculumvitae.CurriculumVitaeRequest;
import com.haunt.job_seeking.dto.response.curriculumvitae.CurriculumVitaeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurriculumVitaeFacadeService {
  CurriculumVitaeResponse create(CurriculumVitaeRequest request);
  void delete(String id);

  List<CurriculumVitaeResponse> list(int size, int page, boolean isAll);
  CurriculumVitaeResponse update(String id, CurriculumVitaeRequest request);
}
