package com.haunt.job_seeking.repository;

import com.haunt.job_seeking.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {
  boolean existsByEmail(String email);

  boolean existsByPhoneNumber(String phoneNumber);
}
