package com.haunt.job_seeking.dto.response.curriculumvitae;

import com.haunt.job_seeking.dto.response.curriculumvitaeattribute.CurriculumVitaeAttributeResponse;
import lombok.Data;

import java.util.List;

@Data
public class CurriculumVitaeResponse {
  private String id;
  private Boolean isShared;
  private String userId;
  private String templateId;
  private List<CurriculumVitaeAttributeResponse> attributes;
}
