package com.haunt.job_seeking.service.address;


import com.haunt.job_seeking.dto.request.address.AddressRequest;
import com.haunt.job_seeking.dto.response.address.AddressPageResponse;
import com.haunt.job_seeking.dto.response.address.AddressResponse;
import com.haunt.job_seeking.entity.address.Address;
import com.haunt.job_seeking.service.base.BaseService;

public interface AddressService extends BaseService<Address> {
  AddressResponse create(AddressRequest request);

  AddressResponse detail(String id);

  AddressPageResponse list(String keyword, int size, int page, boolean isAll);

  AddressResponse update(String id, AddressRequest request);

  void remove(String id);

  Address find(String id);

  AddressResponse retrieve(String id);
}