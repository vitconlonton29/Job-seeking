package com.haunt.job_seeking.repository;

import com.haunt.job_seeking.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends BaseRepository<Account> {
  boolean existsByUsername(String username);

  Optional<Account> findByUsername(String username);
}
