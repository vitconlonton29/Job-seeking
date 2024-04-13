package com.haunt.job_seeking.repository;

import com.haunt.job_seeking.entity.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends BaseRepository<Role> {
  boolean existsByName(String roleName);

  @Query(value = "SELECT r FROM Role r " +
        "WHERE :keyword is null " +
        "or r.name LIKE %:keyword% "
  )
  List<Role> search(@Param("keyword") String keyword, Pageable pageable);

  Role findByName(String roleName);
}
