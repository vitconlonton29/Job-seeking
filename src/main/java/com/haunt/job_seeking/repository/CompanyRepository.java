package com.haunt.job_seeking.repository;

import com.haunt.job_seeking.entity.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends BaseRepository<Company> {

  @Query(value = "SELECT c FROM Company c " +
        "WHERE :keyword is null " +
        "or c.email LIKE %:keyword% "
  )
  List<Company> search(@Param("keyword") String keyword, Pageable pageable);
}