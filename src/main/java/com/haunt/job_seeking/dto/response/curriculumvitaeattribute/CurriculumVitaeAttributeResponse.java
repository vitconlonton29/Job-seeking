package com.haunt.job_seeking.dto.response.curriculumvitaeattribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitaeAttributeResponse {
  private String id;
  private String attributeId;
  private String position;
  private String value;
}
