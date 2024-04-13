package com.haunt.job_seeking.dto.request.curriculumvitae;

import com.haunt.job_seeking.dto.request.curriculumvitaeattribute.CurriculumVitaeAttributeRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitaeRequest {
  private Boolean isShared;
  private String userId;
  private String templateId;
  private List<CurriculumVitaeAttributeRequest> attributes;
}
