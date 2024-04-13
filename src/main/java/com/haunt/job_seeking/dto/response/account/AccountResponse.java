package com.haunt.job_seeking.dto.response.account;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
  private String id;
  private String username;
  private boolean isActive;
  private String roleId;
}
