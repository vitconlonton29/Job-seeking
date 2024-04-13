package com.haunt.job_seeking.exception.address;


import com.haunt.job_seeking.exception.base.NotFoundException;

public class AddressNotFoundException extends NotFoundException {
  public AddressNotFoundException() {
    setCode("com.lawman.shop_sport.exception.address.AddressNotFoundException");
  }

  public AddressNotFoundException(boolean isProvince, boolean isDistrict, boolean isWard) {
    if (isProvince) {
      setCode("com.lawman.shop_sport.exception.address.AddressNotFoundException.Province");
      return;
    }

    if (isDistrict) {
      setCode("com.lawman.shop_sport.exception.address.AddressNotFoundException.District");
      return;
    }

    if (isWard) {
      setCode("com.lawman.shop_sport.exception.address.AddressNotFoundException.Ward");
    }
  }
}
