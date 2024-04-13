package com.haunt.job_seeking.service.impl;

import com.haunt.job_seeking.dto.request.curriculumvitae.CurriculumVitaeRequest;
import com.haunt.job_seeking.dto.response.curriculumvitae.CurriculumVitaeResponse;
import com.haunt.job_seeking.entity.CurriculumVitae;
import com.haunt.job_seeking.repository.CurriculumVitaeRepository;
import com.haunt.job_seeking.exception.curriculumvitae.CurriculumVitaeNotFoundException;
import com.haunt.job_seeking.service.CurriculumVitaeService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.haunt.job_seeking.utils.MapperUtils.*;


@Service
@Slf4j
public class CurriculumVitaeServiceImpl extends BaseServiceImpl<CurriculumVitae> implements CurriculumVitaeService {
  private final CurriculumVitaeRepository repository;


  public CurriculumVitaeServiceImpl(CurriculumVitaeRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public CurriculumVitaeResponse create(CurriculumVitaeRequest request) {
    log.info("(create) request:{}", request);

    CurriculumVitae cv = CurriculumVitae.from(request);

    return toDTO(create(cv), CurriculumVitaeResponse.class);
  }

  @Override
  public List<CurriculumVitaeResponse> list(int size, int page, boolean isAll) {
    log.info("(list) size:{} page:{}  isAll:{}", size, page, isAll);

    List<CurriculumVitae> cvs = isAll ? list() : repository.findAll(PageRequest.of(page, size)).getContent();

    return toDTOs(cvs, CurriculumVitaeResponse.class);
  }

  public CurriculumVitaeResponse update(String id, CurriculumVitaeRequest request) {
    log.info("(update) request:{}", request);

    checkExists(id);
    CurriculumVitae cv = CurriculumVitae.from(request);
    cv.setId(id);

    return toDTO(update(cv), CurriculumVitaeResponse.class);
  }

  private void checkExists(String id) {
    log.info("(checkExists) id:{}", id);

    if (repository.findById(id).isEmpty()) throw new CurriculumVitaeNotFoundException();
  }
}
