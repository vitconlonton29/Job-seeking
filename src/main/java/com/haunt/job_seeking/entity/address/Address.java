package com.haunt.job_seeking.entity.address;

import com.haunt.job_seeking.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address extends BaseEntityWithUpdater {
  private String detail;
  private String wardCode;
  private String districtCode;
  private String provinceCode;
  private boolean isDeleted;
}


