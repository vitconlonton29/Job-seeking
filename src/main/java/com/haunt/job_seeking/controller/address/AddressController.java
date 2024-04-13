package com.haunt.job_seeking.controller.address;

import com.haunt.job_seeking.dto.request.address.AddressRequest;
import com.haunt.job_seeking.dto.response.ResponseGeneral;
import com.haunt.job_seeking.dto.response.address.AddressResponse;
import com.haunt.job_seeking.facade.AddressFacadeService;
import com.haunt.job_seeking.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.haunt.job_seeking.constants.JobSeekingConstants.MessageCode.SUCCESS;


@RestController
@RequestMapping("api/v1/addresses")
@RequiredArgsConstructor
@Slf4j
public class AddressController {
  private final AddressService addressService;
  private final AddressFacadeService addressFacadeService;

  @PostMapping
  public ResponseGeneral<AddressResponse> create(
        @RequestBody AddressRequest request
  ) {
    log.info("(create)request: {}", request);

    return ResponseGeneral.ofCreated(
          SUCCESS,
          addressFacadeService.createAddress(request)
    );
  }

  @GetMapping("{id}")
  public ResponseGeneral<AddressResponse> detail(
        @PathVariable String id
  ) {
    log.info("(detail)id: {}", id);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          addressFacadeService.detailAddress(id)
    );
  }

  @DeleteMapping("{id}")
  public ResponseGeneral<Void> delete(
        @PathVariable String id
  ) {
    log.info("(delete)id: {}", id);

    addressService.remove(id);

    return ResponseGeneral.ofSuccess(
          SUCCESS
    );
  }

  @PutMapping("{id}")
  public ResponseGeneral<AddressResponse> update(
        @PathVariable String id,
        @RequestBody AddressRequest request
  ) {
    log.info("(update)id: {}, request: {}", id, request);

    return ResponseGeneral.ofSuccess(
          SUCCESS,
          addressFacadeService.updateAddress(id, request)
    );
  }

}


