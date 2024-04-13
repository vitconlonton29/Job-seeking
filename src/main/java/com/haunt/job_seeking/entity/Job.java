package com.haunt.job_seeking.entity;

import com.haunt.job_seeking.dto.request.job.JobRequest;
import com.haunt.job_seeking.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs")
public class Job extends BaseEntityWithUpdater {

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

  public static Job from(JobRequest request) {
    Job j = new Job();
    j.title = request.getTitle();
    j.description = request.getDescription();
    j.deadlineSubmission = request.getDeadlineSubmission();
    j.rank = request.getRank();
    j.yearExperienceAmount = request.getYearExperienceAmount();
    j.recruitsAmount = request.getRecruitsAmount();
    j.gender = request.getGender();
    j.workingForm = request.getWorkingForm();
    j.salary = request.getSalary();
    j.salaryCurrencyCode = request.getSalaryCurrencyCode();
    j.addressId = request.getAddressId();
    j.companyId = request.getCompanyId();


    return j;

  }
}
