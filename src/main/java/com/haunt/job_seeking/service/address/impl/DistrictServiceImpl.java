package com.haunt.job_seeking.service.address.impl;

import com.haunt.job_seeking.dto.request.address.SearchDistrictRequest;
import com.haunt.job_seeking.dto.response.address.district.DistrictInfoResponse;
import com.haunt.job_seeking.dto.response.address.district.DistrictPageResponse;
import com.haunt.job_seeking.dto.response.address.district.DistrictResponse;
import com.haunt.job_seeking.entity.address.District;
import com.haunt.job_seeking.exception.address.AddressNotFoundException;
import com.haunt.job_seeking.repository.address.DistrictRepository;
import com.haunt.job_seeking.service.address.DistrictService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Slf4j
public class DistrictServiceImpl extends BaseServiceImpl<District> implements DistrictService {

  private final DistrictRepository repository;

  public DistrictServiceImpl(DistrictRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public DistrictPageResponse search(SearchDistrictRequest request, int page, int size, boolean isAll) {
    log.info("(search) request: {}, page:{}, size:{}, isAll: {}", request, page, size, isAll);

    String keyword = (request == null || request.getKeyword() == null) ? null : request.getKeyword().toLowerCase();
    String provinceCode = (request == null) ? null : request.getProvinceCode();

    List<DistrictResponse> districts = isAll ? repository.list(provinceCode) :
          repository.search(keyword, provinceCode, PageRequest.of(page, size));

    int totalAmount = isAll ? repository.count(provinceCode) : repository.countSearch(keyword, provinceCode);

    return DistrictPageResponse.of(
          districts,
          totalAmount
    );
  }

  @Override
  public DistrictInfoResponse detail(String code) {
    log.info("(detail)code: {}", code);

    return repository.get(code);
  }

  @Override
  public void checkDistrictExist(String code) {
    log.info("(checkDistrictExist)");

    if (!repository.existsByCode(code)) {
      log.error("(checkDistrictExist) ============= AddressExistException");
      throw new AddressNotFoundException(false, true, false);
    }
  }
}
