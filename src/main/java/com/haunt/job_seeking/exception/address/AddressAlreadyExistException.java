package com.haunt.job_seeking.exception.address;


import com.haunt.job_seeking.exception.base.ConflictException;

public class AddressAlreadyExistException extends ConflictException {

  public AddressAlreadyExistException() {
    setCode("com.lawman.shop_sport.exception.AddressExistException");
  }
}
