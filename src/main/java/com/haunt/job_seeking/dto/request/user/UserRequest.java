package com.haunt.job_seeking.dto.request.user;

import com.haunt.job_seeking.dto.request.auth.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
  private String fullName;
  private Integer sex;
  private String phoneNumber;
  private String email;
  private String address;
  private Date dob;

  public static UserRequest from(RegistrationRequest request) {
    UserRequest userRequest = new UserRequest();
    userRequest.fullName = request.getFullName();
    userRequest.sex = request.getSex();
    userRequest.phoneNumber = request.getPhoneNumber();
    userRequest.email = request.getEmail();
    userRequest.address = request.getAddress();
    userRequest.dob = request.getDob();
    return userRequest;
  }
}
