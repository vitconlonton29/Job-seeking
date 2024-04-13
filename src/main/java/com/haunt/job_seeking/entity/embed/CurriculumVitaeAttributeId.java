package com.haunt.job_seeking.entity.embed;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CurriculumVitaeAttributeId implements Serializable {
  private String attributeId;
  private String curriculumVitaeId;
}
