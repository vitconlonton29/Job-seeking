package com.haunt.job_seeking.dto.response.job;

import lombok.Data;

@Data
public class JobResponse {
  private String id;
  private String title;
  private String description;
  private Long deadlineSubmission;
  private String rank;
  private Integer yearExperienceAmount;
  private Integer recruitsAmount;
  private String gender;
  private String workingForm;
  private Double salary;
  private String salaryCurrencyCode;
  private String companyId;
  private String addressId;
}
