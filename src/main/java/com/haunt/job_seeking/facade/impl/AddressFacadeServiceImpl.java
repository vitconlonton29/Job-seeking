package com.haunt.job_seeking.facade.impl;

import com.haunt.job_seeking.dto.request.address.AddressRequest;
import com.haunt.job_seeking.dto.response.address.AddressResponse;
import com.haunt.job_seeking.entity.address.Address;
import com.haunt.job_seeking.facade.AddressFacadeService;
import com.haunt.job_seeking.service.address.AddressService;
import com.haunt.job_seeking.service.address.DistrictService;
import com.haunt.job_seeking.service.address.ProvinceService;
import com.haunt.job_seeking.service.address.WardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class AddressFacadeServiceImpl implements AddressFacadeService {
  private final AddressService addressService;
  private final DistrictService districtService;
  private final ProvinceService provinceService;
  private final WardService wardService;


  @Override
  @Transactional
  public AddressResponse createAddress(AddressRequest request) {
    log.info("(createAddress)request: {}", request);

    this.checkAddressComponentsExist(request);

    AddressResponse response = addressService.create(request);

    this.setProperties(response, request);

    return response;
  }

  @Override
  public AddressResponse detailAddress(String id) {
    log.info("(detailAddress)id: {}", id);

    AddressResponse response = new AddressResponse();
    Address address = addressService.find(id);

    this.setProperties(response, address);

    return response;
  }

  @Override
  public AddressResponse updateAddress(String id, AddressRequest request) {
    log.info("(updateAddress)id: {}, request: {}", id, request);

    this.checkAddressComponentsExist(request);

    AddressResponse response = addressService.update(id, request);

    this.setProperties(response, request);

    return response;
  }

  private void checkAddressComponentsExist(AddressRequest request) {
    log.info("(checkAddressComponentsExist)start");

    if (Objects.nonNull(request.getProvinceCode()))
      provinceService.checkProvinceExist(request.getProvinceCode());

    if (Objects.nonNull(request.getDistrictCode()))
      districtService.checkDistrictExist(request.getDistrictCode());

    if (Objects.nonNull(request.getWardCode()))
      wardService.checkWardExist(request.getWardCode());
  }

  private void setProperties(AddressResponse response, AddressRequest request) {
    log.info("(setProperties)response: {}, request: {}", response, request);

    if (Objects.nonNull(request.getProvinceCode()))
      response.setProvinces(provinceService.detail(request.getProvinceCode()));

    if (Objects.nonNull(request.getDistrictCode()))
      response.setDistricts(districtService.detail(request.getDistrictCode()));

    if (Objects.nonNull(request.getWardCode()))
      response.setWards(wardService.detail(request.getWardCode()));
  }

  private void setProperties(AddressResponse response, Address address) {
    log.info("(setProperties)response: {}, address: {}", response, address);

    response.setId(address.getId());

    response.setWards(
          Objects.nonNull(address.getWardCode()) ?
                wardService.detail(address.getWardCode()) : null
    );

    response.setDistricts(
          Objects.nonNull(address.getDistrictCode()) ?
                districtService.detail(address.getDistrictCode()) : null
    );
    response.setProvinces(
          Objects.nonNull(address.getProvinceCode()) ?
                provinceService.detail(address.getProvinceCode()) : null
    );

    response.setDetail(address.getDetail());
  }

}
