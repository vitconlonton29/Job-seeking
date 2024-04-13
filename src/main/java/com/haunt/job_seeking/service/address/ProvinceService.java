package com.haunt.job_seeking.service.address;


import com.haunt.job_seeking.dto.response.address.province.ProvinceInfoResponse;
import com.haunt.job_seeking.dto.response.address.province.ProvincePageResponse;
import com.haunt.job_seeking.entity.address.Province;
import com.haunt.job_seeking.service.base.BaseService;

public interface ProvinceService extends BaseService<Province> {
  ProvincePageResponse search(String keyword, int page, int size, boolean isAll);

  ProvinceInfoResponse detail(String code);

  void checkProvinceExist(String code);
}
