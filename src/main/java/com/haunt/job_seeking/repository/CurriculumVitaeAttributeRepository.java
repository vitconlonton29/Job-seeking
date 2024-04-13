package com.haunt.job_seeking.repository;

import com.haunt.job_seeking.entity.CurriculumVitaeAttribute;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CurriculumVitaeAttributeRepository extends BaseRepository<CurriculumVitaeAttribute> {

  @Modifying
  @Transactional
  @Query("DELETE FROM CurriculumVitaeAttribute cva WHERE cva.id.curriculumVitaeId = :cvId")
  void deleteByCurriculumVitaeId(@Param("cvId") String cvId);

}

