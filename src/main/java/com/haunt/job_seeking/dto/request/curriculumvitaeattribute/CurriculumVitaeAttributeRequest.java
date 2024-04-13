package com.haunt.job_seeking.dto.request.curriculumvitaeattribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitaeAttributeRequest {
  private String curriculumVitaeId;
  private String attributeId;
  private Integer position;
  private String value;
}
