
package com.haunt.job_seeking.repository;

import com.haunt.job_seeking.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends BaseRepository<Image> {
  List<Image> findByCompanyId(String companyId);
}