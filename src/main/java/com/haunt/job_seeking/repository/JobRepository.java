package com.haunt.job_seeking.repository;

import com.haunt.job_seeking.entity.Job;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends BaseRepository<Job>{
  @Query(value = "SELECT j FROM Job j " +
        "WHERE :keyword is null " +
        "or j.title LIKE %:keyword% "
  )
  List<Job> search(@Param("keyword") String keyword, Pageable pageable);
}
