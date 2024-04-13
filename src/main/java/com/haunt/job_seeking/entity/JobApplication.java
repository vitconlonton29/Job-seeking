package com.haunt.job_seeking.entity;

import com.haunt.job_seeking.dto.request.jobapplication.JobApplicationRequest;
import com.haunt.job_seeking.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "job_application")
public class JobApplication extends BaseEntityWithUpdater {
  private String userId;
  private String curriculumVitaeId;
  private String jobId;

  public static JobApplication from(JobApplicationRequest request) {
    JobApplication ja = new JobApplication();
    ja.userId = request.getUserId();
    ja.curriculumVitaeId = request.getCvId();
    ja.jobId = request.getJobId();

    return ja;
  }
}
