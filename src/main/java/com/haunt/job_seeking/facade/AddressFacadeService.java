package com.haunt.job_seeking.facade;


import com.haunt.job_seeking.dto.request.address.AddressRequest;
import com.haunt.job_seeking.dto.response.address.AddressResponse;

public interface AddressFacadeService {
  AddressResponse createAddress(AddressRequest request);

  AddressResponse detailAddress(String id);

  AddressResponse updateAddress(String id, AddressRequest request);
}
