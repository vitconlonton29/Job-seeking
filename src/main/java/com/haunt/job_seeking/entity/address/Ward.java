package com.haunt.job_seeking.entity.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wards")
public class Ward {
  @Id
  private String code;
  private String name;
  private String nameEn;
  private String fullName;
  private String fullNameEn;
  private String codeName;
  private String districtCode;
}
