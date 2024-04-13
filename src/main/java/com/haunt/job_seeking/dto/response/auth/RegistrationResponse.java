package com.haunt.job_seeking.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
  private String id;
  private String fullName;
  private Integer sex;
  private String phoneNumber;
  private String email;
  private String address;
  private Date dob;
  private String username;
  private String roleId;
}
