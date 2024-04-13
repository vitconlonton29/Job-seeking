package com.haunt.job_seeking.controller.address;

import com.haunt.job_seeking.dto.request.address.SearchDistrictRequest;
import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.address.district.DistrictPageResponse;
import com.haunt.job_seeking.service.address.DistrictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/v1/districts")
@RequiredArgsConstructor
public class DistrictController {
  private final DistrictService districtService;

  @PostMapping
  public ResponseGeneral<DistrictPageResponse> list(
        @RequestBody(required = false) SearchDistrictRequest request,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "all", defaultValue = "false") boolean isAll
  ) {
    log.info("(search) request: {}, page:{}, size:{}", request, page, size);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          districtService.search(request, page, size, isAll)
    );
  }
}
