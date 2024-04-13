package com.haunt.job_seeking.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
  private String fullName;

  @NotNull(message = "Sex cannot be null")
  private Integer sex;

  private String phoneNumber;

  private String email;

  @NotBlank(message = "Address cannot be blank")
  private String address;

  private Date dob;

  private String username;

  private String password;

  private String type;
}
