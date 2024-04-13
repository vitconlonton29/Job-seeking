package com.haunt.job_seeking.entity.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "provinces")
public class Province {
  @Id
  private String code;
  private String name;
  private String nameEn;
  private String fullName;
  private String fullNameEn;
  private String codeName;
}
