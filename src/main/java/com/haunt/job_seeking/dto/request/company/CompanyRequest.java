
package com.haunt.job_seeking.dto.request.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
  private String link;
  private String description;
  private Integer employeeNumber;
  private String email;
  private String taxCode;
  private String phoneNumber;
  private String addressId;
  private String employerId;
  private List<String> imageIds;
}