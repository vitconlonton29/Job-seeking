package com.haunt.job_seeking.controller;

import com.haunt.job_seeking.dto.request.job.JobRequest;
import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.job.JobResponse;
import com.haunt.job_seeking.facade.JobFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@RestController
@RequestMapping("api/v1/jobs")
@RequiredArgsConstructor
@Slf4j
public class JobController {
  private final JobFacadeService jobFacadeService;

  @PostMapping
  public ResponseGeneral<JobResponse> create(@RequestBody JobRequest request) {
    log.info("(create) request:{}", request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          jobFacadeService.create(request)
    );
  }

  @GetMapping
  ResponseGeneral<List<JobResponse>> list(
        @RequestParam(value = "name", required = false) String keyword,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "isAll", defaultValue = "true") boolean isAll
  ){
    log.info("(list) , pageNo: {} , pageSize: {} , isAll: {} , keyword: {}", page, size, isAll, keyword);

    return ResponseGeneral.of(
          HttpStatus.OK.value(),
          SUCCESS,
          jobFacadeService.list(size, page, isAll, keyword)
    );
  }
  @DeleteMapping("/{id}")
  public ResponseGeneral<Void> delete(@PathVariable String id) {
    log.info("(delete) id:{}", id);

    jobFacadeService.delete(id);

    return ResponseGeneral.ofSuccess(SUCCESS);
  }
  @PutMapping("{id}")
  public ResponseGeneral<JobResponse> update(@PathVariable String id, @RequestBody JobRequest request) {
    log.info("(update) id:{} request:{}", id, request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          jobFacadeService.update(id, request)
    );
  }


}
