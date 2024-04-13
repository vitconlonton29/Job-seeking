package com.haunt.job_seeking.service.address.impl;

import com.haunt.job_seeking.dto.response.address.province.ProvinceInfoResponse;
import com.haunt.job_seeking.dto.response.address.province.ProvincePageResponse;
import com.haunt.job_seeking.dto.response.address.province.ProvinceResponse;
import com.haunt.job_seeking.entity.address.Province;
import com.haunt.job_seeking.exception.address.AddressNotFoundException;
import com.haunt.job_seeking.repository.address.ProvinceRepository;
import com.haunt.job_seeking.service.address.ProvinceService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Slf4j
public class ProvinceServiceImpl extends BaseServiceImpl<Province> implements ProvinceService {
  private final ProvinceRepository repository;

  public ProvinceServiceImpl(ProvinceRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public ProvincePageResponse search(String keyword, int page, int size, boolean isAll) {
    log.info("(search) keyword: {}, page:{}, size:{}, isAll: {}", keyword, page, size, isAll);

    keyword = (keyword == null) ? null : keyword.toLowerCase();

    List<ProvinceResponse> provinces = isAll ?
          repository.list() : repository.search(keyword, PageRequest.of(page, size));

    int totalAmount = isAll ? (int) repository.count() : repository.countSearch(keyword);

    return ProvincePageResponse.of(
          provinces,
          totalAmount
    );
  }

  @Override
  public ProvinceInfoResponse detail(String code) {
    log.info("(detail)code: {}", code);

    return repository.get(code);
  }

  @Override
  public void checkProvinceExist(String code) {
    log.info("(checkProvinceExist)");

    if (!repository.existsByCode(code)) {
      log.error("(checkProvinceExist) =============> AddressNotFoundException");

      throw new AddressNotFoundException(true, false, false);
    }
  }
}
