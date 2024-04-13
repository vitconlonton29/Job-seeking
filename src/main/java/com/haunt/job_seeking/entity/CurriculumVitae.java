package com.haunt.job_seeking.entity;

import com.haunt.job_seeking.dto.request.curriculumvitae.CurriculumVitaeRequest;
import com.haunt.job_seeking.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Table(name = "curriculum_vitae")
@NoArgsConstructor
public class CurriculumVitae extends BaseEntityWithUpdater {
  private Boolean isShared;
  private String userId;
  private String templateId;
  public static CurriculumVitae from(CurriculumVitaeRequest request){
    CurriculumVitae cv = new CurriculumVitae();
    cv.isShared = request.getIsShared();
    cv.userId = request.getUserId();
    cv.templateId = request.getTemplateId();

    return cv;
  }
}
