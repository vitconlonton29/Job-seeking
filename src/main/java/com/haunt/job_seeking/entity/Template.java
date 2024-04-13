package com.haunt.job_seeking.entity;

import com.haunt.job_seeking.entity.base.BaseEntityWithUpdater;
import com.haunt.job_seeking.entity.enums.TemplateType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "templates")
public class Template extends BaseEntityWithUpdater {
  private String name;

  @Enumerated(EnumType.STRING)
  private TemplateType type;
}
