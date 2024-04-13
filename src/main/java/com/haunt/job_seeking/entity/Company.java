package com.haunt.job_seeking.entity;

import com.haunt.job_seeking.dto.request.company.CompanyRequest;
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
@Table(name = "companies")
public class Company extends BaseEntityWithUpdater {
  private String link;
  private String description;
  private Integer employeeNumber;
  private String email;
  private String taxCode;
  private String phoneNumber;
  private String addressId;
  private Boolean isDeleted;
  private Boolean is_verified;

  public static Company from(CompanyRequest request) {
    Company company = new Company();
    company.link = request.getLink();
    company.description = request.getDescription();
    company.employeeNumber = request.getEmployeeNumber();
    company.email = request.getEmail();
    company.taxCode = request.getTaxCode();
    company.phoneNumber = request.getPhoneNumber();
    company.addressId = request.getAddressId();
    return company;
  }
}
