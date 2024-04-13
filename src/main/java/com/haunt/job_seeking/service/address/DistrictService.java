package com.haunt.job_seeking.service.address;


import com.haunt.job_seeking.dto.request.address.SearchDistrictRequest;
import com.haunt.job_seeking.dto.response.address.district.DistrictInfoResponse;
import com.haunt.job_seeking.dto.response.address.district.DistrictPageResponse;
import com.haunt.job_seeking.entity.address.District;
import com.haunt.job_seeking.service.base.BaseService;

public interface DistrictService extends BaseService<District> {
  DistrictPageResponse search(SearchDistrictRequest request, int page, int size, boolean isAll);

  DistrictInfoResponse detail(String code);

  void checkDistrictExist(String code);
}
