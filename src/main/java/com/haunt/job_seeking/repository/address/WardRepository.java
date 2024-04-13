package com.haunt.job_seeking.repository.address;

import com.haunt.job_seeking.dto.response.address.ward.WardInfoResponse;
import com.haunt.job_seeking.dto.response.address.ward.WardResponse;
import com.haunt.job_seeking.entity.address.Ward;
import com.haunt.job_seeking.repository.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardRepository extends BaseRepository<Ward> {
  boolean existsByCode(String code);

  @Query("SELECT new com.haunt.job_seeking.dto.response.address.ward.WardInfoResponse " +
        "(w.name, w.nameEn, w.codeName, w.code) " +
        "FROM Ward w " +
        "WHERE w.code = :code")
  WardInfoResponse get(String code);

  @Query("SELECT new com.haunt.job_seeking.dto.response.address.ward.WardResponse " +
        "(w.code, w.name, w.nameEn, w.fullName, w.fullNameEn, w.codeName) " +
        "FROM Ward w " +
        "WHERE (:keyword is null or ( " +
        "lower(w.name) LIKE :keyword% OR " +
        "lower(w.nameEn) LIKE :keyword% OR " +
        "lower(w.fullName) LIKE :keyword% OR " +
        "lower(w.fullNameEn) LIKE :keyword% OR " +
        "lower(w.codeName) LIKE :keyword%)) " +
        "and (:districtCode is null or w.districtCode = :districtCode) " +
        "ORDER by w.name")
  List<WardResponse> search(String keyword, String districtCode, Pageable pageable);

  @Query("SELECT new com.haunt.job_seeking.dto.response.address.ward.WardResponse " +
        "(w.code, w.name, w.nameEn, w.fullName, w.fullNameEn, w.codeName) " +
        "FROM Ward w " +
        "WHERE (:districtCode is null or w.districtCode = :districtCode) ORDER by w.name")
  List<WardResponse> list(String districtCode);

  @Query("SELECT COUNT(DISTINCT w.code) " +
        "FROM Ward w " +
        "WHERE (:keyword is null or ( " +
        "lower(w.name) LIKE :keyword% OR " +
        "lower(w.nameEn) LIKE :keyword% OR " +
        "lower(w.fullName) LIKE :keyword% OR " +
        "lower(w.fullNameEn) LIKE :keyword% OR " +
        "lower(w.codeName) LIKE :keyword%)) " +
        "and (:districtCode is null or w.districtCode = :districtCode)")
  int countSearch(String keyword, String districtCode);

  @Query("SELECT COUNT(DISTINCT w.code) " +
        "FROM Ward w " +
        "WHERE (:districtCode is null or w.districtCode = :districtCode)")
  int count(String districtCode);

}
