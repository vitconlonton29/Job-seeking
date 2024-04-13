package com.haunt.job_seeking.entity;

import com.haunt.job_seeking.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntityWithUpdater {
  private String fullName;
  private Integer sex;
  private String phoneNumber;
  private String email;
  private String address;
  private Date dob;
}
