package com.haunt.job_seeking.service.address.impl;

import com.haunt.job_seeking.dto.request.address.SearchWardRequest;
import com.haunt.job_seeking.dto.response.address.ward.WardInfoResponse;
import com.haunt.job_seeking.dto.response.address.ward.WardPageResponse;
import com.haunt.job_seeking.dto.response.address.ward.WardResponse;
import com.haunt.job_seeking.entity.address.Ward;
import com.haunt.job_seeking.exception.address.AddressNotFoundException;
import com.haunt.job_seeking.repository.address.WardRepository;
import com.haunt.job_seeking.service.address.WardService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Slf4j
public class WardServiceImpl extends BaseServiceImpl<Ward> implements WardService {

  private final WardRepository repository;

  public WardServiceImpl(WardRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public WardPageResponse search(SearchWardRequest request, int page, int size, boolean isAll) {
    log.info("(search) request: {}, page:{}, size:{}, isAll: {}", request, page, size, isAll);

    String keyword = (request == null || request.getKeyword() == null) ?
          null : request.getKeyword().toLowerCase();

    String districtCode = (request == null) ?
          null : request.getDistrictCode();

    List<WardResponse> wards = isAll ?
          repository.list(districtCode) : repository.search(keyword, districtCode, PageRequest.of(page, size));

    int totalAmount = isAll ?
          repository.count(districtCode) : repository.countSearch(keyword, districtCode);

    return new WardPageResponse(
          wards,
          totalAmount
    );
  }

  @Override
  public WardInfoResponse detail(String code) {
    log.info("(detail)code: {}", code);

    return repository.get(code);
  }

  @Override
  public void checkWardExist(String code) {
    log.info("(checkDistrictExist)");

    if (!repository.existsByCode(code)) {
      log.error("(checkDistrictExist) ============= AddressNotFoundException");

      throw new AddressNotFoundException(false, false, true);
    }
  }
}
