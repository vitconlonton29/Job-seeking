package com.haunt.job_seeking.entity;

import com.haunt.job_seeking.dto.request.curriculumvitaeattribute.CurriculumVitaeAttributeRequest;
import com.haunt.job_seeking.entity.embed.CurriculumVitaeAttributeId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "curriculum_vitae_attribute")
public class CurriculumVitaeAttribute {
  @EmbeddedId
  private CurriculumVitaeAttributeId id;

  public static CurriculumVitaeAttribute of(String attributeId, String curriculumVitaeId) {
    var curriculumVitaeAttribute = new CurriculumVitaeAttribute();
    curriculumVitaeAttribute.setId(
          CurriculumVitaeAttributeId.of(attributeId, curriculumVitaeId)
    );

    return curriculumVitaeAttribute;
  }

  private Integer position;
  private String value;

  public static CurriculumVitaeAttribute from(CurriculumVitaeAttributeRequest request) {
    CurriculumVitaeAttribute cva = CurriculumVitaeAttribute.of(request.getAttributeId(), request.getCurriculumVitaeId());
    cva.setPosition(request.getPosition());
    cva.setValue(request.getValue());

    return cva;
  }
}
