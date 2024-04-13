package com.haunt.job_seeking.controller;

import com.haunt.job_seeking.dto.request.company.CompanyRequest;
import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.company.CompanyResponse;
import com.haunt.job_seeking.facade.CompanyFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@RestController
@RequestMapping("api/v1/companies")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
  private final CompanyFacadeService companyFacadeService;

  @PostMapping
  public ResponseGeneral<CompanyResponse> create(
        @RequestBody CompanyRequest request
  ) {
    log.info("(create) request:{}", request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          companyFacadeService.create(request)
    );
  }

  @DeleteMapping("/{id}")
  public ResponseGeneral<Void> delete(@PathVariable String id) {
    log.info("(delete) id:{}", id);

    companyFacadeService.delete(id);

    return ResponseGeneral.ofSuccess(SUCCESS);

  }


  @GetMapping("/{id}")
  public ResponseGeneral<CompanyResponse> detail(@PathVariable String id) {
    log.info("(detail) id:{}", id);

    return ResponseGeneral.of(
          HttpStatus.OK.value(),
          SUCCESS,
          companyFacadeService.detail(id));
  }

  @PutMapping("/{id}")
  public ResponseGeneral<CompanyResponse> update(@PathVariable String id, @RequestBody CompanyRequest request) {
    log.info("(update) id:{} request:{}", id, request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          companyFacadeService.update(id, request)

    );
  }

  @GetMapping
  ResponseGeneral<List<CompanyResponse>> list(
        @RequestParam(value = "name", required = false) String keyword,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "isAll", defaultValue = "true") boolean isAll
  ) {
    log.info("(list) , pageNo: {} , pageSize: {} , isAll: {} , keyword: {}", page, size, isAll, keyword);

    return ResponseGeneral.of(
          HttpStatus.OK.value(),
          SUCCESS,
          companyFacadeService.list(size, page, isAll, keyword)
    );
  }

  @PutMapping("/{id}/verify")
  public ResponseGeneral<CompanyResponse> verify(@PathVariable String id) {
    log.info("(verify) id:{}", id);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          companyFacadeService.verify(id)
    );
  }


}
