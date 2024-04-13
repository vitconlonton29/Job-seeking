package com.haunt.job_seeking.controller.address;

import com.haunt.job_seeking.dto.request.address.SearchWardRequest;
import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.address.ward.WardPageResponse;
import com.haunt.job_seeking.service.address.WardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/v1/wards")
@RequiredArgsConstructor
public class WardController {
  private final WardService wardService;

  @PostMapping
  public ResponseGeneral<WardPageResponse> list(
        @RequestBody(required = false) SearchWardRequest request,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "all", defaultValue = "false") boolean isAll
  ) {
    log.info("(search) request: {}, page:{}, size:{}", request, page, size);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          wardService.search(request, page, size, isAll)
    );
  }
}
