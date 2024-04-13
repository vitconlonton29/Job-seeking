package com.haunt.job_seeking.service.address;


import com.haunt.job_seeking.dto.request.address.SearchWardRequest;
import com.haunt.job_seeking.dto.response.address.ward.WardInfoResponse;
import com.haunt.job_seeking.dto.response.address.ward.WardPageResponse;
import com.haunt.job_seeking.entity.address.Ward;
import com.haunt.job_seeking.service.base.BaseService;

public interface WardService extends BaseService<Ward> {
  WardPageResponse search(SearchWardRequest request, int page, int size, boolean isAll);

  WardInfoResponse detail(String code);

  void checkWardExist(String code);
}
