package com.haunt.job_seeking.service.address.impl;


import com.haunt.job_seeking.dto.request.address.AddressRequest;
import com.haunt.job_seeking.dto.response.address.AddressPageResponse;
import com.haunt.job_seeking.dto.response.address.AddressResponse;
import com.haunt.job_seeking.entity.address.Address;
import com.haunt.job_seeking.exception.address.AddressNotFoundException;
import com.haunt.job_seeking.repository.address.AddressRepository;
import com.haunt.job_seeking.service.address.AddressService;
import com.haunt.job_seeking.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.haunt.job_seeking.utils.MapperUtils.*;


@Slf4j
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService {
  private final AddressRepository repository;

  public AddressServiceImpl(AddressRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  @Transactional
  public AddressResponse create(AddressRequest request) {
    log.info("(create) request: {}", request);

    Address address = toEntity(request, Address.class);

    return toDTO(create(address), AddressResponse.class);
  }

  @Override
  public AddressResponse detail(String id) {
    log.info("(detail)id: {}", id);

    return toDTO(find(id), AddressResponse.class);
  }

  @Override
  public AddressPageResponse list(String keyword, int size, int page, boolean isAll) {
    log.info("(list)name: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);

    List<Address> addresses = isAll ?
          repository.findAll() : repository.search(PageRequest.of(page, size), keyword);

    List<AddressResponse> addressResponses = addresses.stream()
          .map(address -> MODEL_MAPPER.map(address, AddressResponse.class))
          .toList();

    return AddressPageResponse.of(
          addressResponses,
          isAll ? (int) repository.count() : repository.countSearch(keyword)
    );
  }

  @Override
  @Transactional
  public AddressResponse update(String id, AddressRequest request) {
    log.info("(update)id: {}, request: {}", id, request);

    var existingAddress = find(id);

    if (isChanged(existingAddress, request)) {
      this.setProperties(existingAddress, request);

      return toDTO(update(existingAddress), AddressResponse.class);
    }

    return toDTO(existingAddress, AddressResponse.class);
  }

  @Override
  @Transactional
  public void remove(String id) {
    log.info("(remove)id: {}", id);

    repository.softDelete(find(id).getId());
  }

  @Override
  public AddressResponse retrieve(String id) {
    log.info("(retrieve) id: {}", id);

    return repository.retrieve(id);
  }

  @Override
  public Address find(String id) {
    log.info("(find)id: {}", id);

    return repository.findById(id).orElseThrow(AddressNotFoundException::new);
  }

  private boolean isChanged(Address existingAddress, AddressRequest request) {
    String existingProvinceCode = existingAddress.getProvinceCode();
    String existingDistrictCode = existingAddress.getDistrictCode();
    String existingWardCode = existingAddress.getWardCode();
    String existingDetail = existingAddress.getDetail();

    return !Objects.equals(existingProvinceCode, request.getProvinceCode())
          || !Objects.equals(existingDistrictCode, request.getDistrictCode())
          || !Objects.equals(existingWardCode, request.getWardCode())
          || !Objects.equals(existingDetail, request.getDetail());
  }

  private void setProperties(Address address, AddressRequest request) {
    log.info("(setProperties)address: {}, request: {}", address, request);

    address.setProvinceCode(request.getProvinceCode());
    address.setDistrictCode(request.getDistrictCode());
    address.setWardCode(request.getWardCode());
    address.setDetail(request.getDetail());
  }

}
