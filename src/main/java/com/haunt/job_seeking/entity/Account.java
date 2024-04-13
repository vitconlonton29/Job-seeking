package com.haunt.job_seeking.entity;

import com.haunt.job_seeking.dto.request.account.AccountRequest;
import com.haunt.job_seeking.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntityWithUpdater {
  private String username;
  private String password;
  private Integer isActive;
  private String roleId;

  public static Account from(AccountRequest request) {
    Account account = new Account();
    account.username = request.getUsername();
    account.roleId = request.getRoleId();
    return account;
  }
}
