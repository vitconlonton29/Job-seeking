package com.haunt.job_seeking.controller;

import com.haunt.job_seeking.dto.request.curriculumvitae.CurriculumVitaeRequest;
import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.curriculumvitae.CurriculumVitaeResponse;
import com.haunt.job_seeking.facade.CurriculumVitaeFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@RestController
@RequestMapping("api/v1/curriculum-vitaes")
@RequiredArgsConstructor
@Slf4j
public class CurriculumVitaeController {
  private final CurriculumVitaeFacadeService curriculumVitaeFacadeService;

  @PostMapping
  public ResponseGeneral<CurriculumVitaeResponse> create(@RequestBody CurriculumVitaeRequest request) {
    log.info("(create) request:{}", request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          curriculumVitaeFacadeService.create(request)
    );
  }

  @DeleteMapping("/{id}")
  public ResponseGeneral<Void> delete(@PathVariable String id) {
    log.info("(delete) id:{}", id);

    curriculumVitaeFacadeService.delete(id);

    return ResponseGeneral.ofSuccess(SUCCESS);

  }

  @GetMapping
  public ResponseGeneral<List<CurriculumVitaeResponse>> list(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "isAll", defaultValue = "true") boolean isAll) {
    log.info("(list) , pageNo: {} , pageSize: {} , isAll: {} ", page, size, isAll);

    return ResponseGeneral.of(
          HttpStatus.OK.value(),
          SUCCESS,
          curriculumVitaeFacadeService.list(size, page, isAll)
    );
  }

  @PutMapping("/{id}")
  public ResponseGeneral<CurriculumVitaeResponse> update(@PathVariable String id, @RequestBody CurriculumVitaeRequest request) {
    log.info("(update) request:{}", request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          curriculumVitaeFacadeService.update(id, request)
    );
  }

}
