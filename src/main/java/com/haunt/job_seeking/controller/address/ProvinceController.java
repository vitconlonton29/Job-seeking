package com.haunt.job_seeking.controller.address;

import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.address.province.ProvincePageResponse;
import com.haunt.job_seeking.service.address.ProvinceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/v1/provinces")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProvinceController {
  private final ProvinceService provinceService;

  @GetMapping
  public ResponseGeneral<ProvincePageResponse> list(
        @RequestParam(name = "keyword", required = false) String keyword,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "all", defaultValue = "false") boolean isAll
  ) {
    log.info("(search) keyword: {}, page:{}, size:{}", keyword, page, size);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          provinceService.search(keyword, page, size, isAll)
    );
  }
}
