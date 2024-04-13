package com.haunt.job_seeking.controller;

import com.haunt.job_seeking.dto.request.role.RoleRequest;
import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.role.RoleResponse;
import com.haunt.job_seeking.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;

@Slf4j
@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
  private final RoleService roleService;

  @PostMapping
  public ResponseGeneral<RoleResponse> create(@RequestBody RoleRequest request) {
    log.info("(create) request:{}", request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          roleService.create(request)
    );
  }

  @GetMapping("/{id}")
  public ResponseGeneral<RoleResponse> get(@PathVariable String id) {
    log.info("(get) id:{}", id);

    return ResponseGeneral.of(
          HttpStatus.OK.value(),
          SUCCESS,
          roleService.detail(id)
    );
  }

  @DeleteMapping("/{id}")
  public ResponseGeneral<Void> delete(@PathVariable String id) {
    log.info("(delete) id:{}", id);

    roleService.delete(id);

    return ResponseGeneral.ofSuccess(SUCCESS);

  }

  @PutMapping("/{id}")
  public ResponseGeneral<RoleResponse> update(@PathVariable String id, @RequestBody RoleRequest request) {
    log.info("(update) id:{} request:{}", id, request);

    return ResponseGeneral.of(
          HttpStatus.OK.value(),
          SUCCESS,
          roleService.update(id, request)
    );

  }


  @GetMapping
  ResponseGeneral<List<RoleResponse>> list(
        @RequestParam(value = "name", required = false) String keyword,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "isAll", defaultValue = "true") boolean isAll
  ) {
    log.info("(list) , pageNo: {} , pageSize: {} , isAll: {} , keyword: {}", page, size, isAll, keyword);

    return ResponseGeneral.of(
          HttpStatus.OK.value(),
          SUCCESS,
          roleService.list(size, page, isAll, keyword)
    );
  }
}
