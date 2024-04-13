package com.haunt.job_seeking.repository.address;

import com.haunt.job_seeking.dto.response.address.district.DistrictInfoResponse;
import com.haunt.job_seeking.dto.response.address.district.DistrictResponse;
import com.haunt.job_seeking.entity.address.District;
import com.haunt.job_seeking.repository.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictRepository extends BaseRepository<District> {
  boolean existsByCode(String code);

  @Query("SELECT new com.haunt.job_seeking.dto.response.address.district.DistrictInfoResponse " +
        "(d.name, d.nameEn, d.codeName, d.code) " +
        "FROM District d " +
        "WHERE d.code = :code")
  DistrictInfoResponse get(String code);

  @Query("SELECT new com.haunt.job_seeking.dto.response.address.district.DistrictResponse " +
        "(d.code, d.name, d.nameEn, d.fullName, d.fullNameEn, d.codeName) " +
        "FROM District d " +
        "WHERE (:keyword is null or ( " +
        "lower( d.name) LIKE :keyword% OR " +
        "lower( d.nameEn) LIKE :keyword% OR " +
        "lower( d.fullName) LIKE :keyword% OR " +
        "lower( d.fullNameEn) LIKE :keyword% OR " +
        "lower( d.codeName) LIKE :keyword%)) " +
        "and (:provinceCode is null  or d.provinceCode = :provinceCode) " +
        "order by d.name")
  List<DistrictResponse> search(String keyword, String provinceCode, Pageable pageable);

  @Query("SELECT new com.haunt.job_seeking.dto.response.address.district.DistrictResponse " +
        "(d.code, d.name, d.nameEn, d.fullName, d.fullNameEn, d.codeName) " +
        "FROM District d " +
        "WHERE (:provinceCode is null or d.provinceCode = :provinceCode) " +
        "order by d.name")
  List<DistrictResponse> list(String provinceCode);


  @Query("SELECT count(DISTINCT d.code) " +
        "FROM District d " +
        "WHERE (:keyword is null or ( " +
        "lower( d.name) LIKE :keyword% OR " +
        "lower( d.nameEn) LIKE :keyword% OR " +
        "lower( d.fullName) LIKE :keyword% OR " +
        "lower( d.fullNameEn) LIKE :keyword% OR " +
        "lower( d.codeName) LIKE :keyword%)) " +
        "and (:provinceCode is null  or d.provinceCode = :provinceCode)")
  int countSearch(String keyword, String provinceCode);

  @Query("SELECT COUNT (DISTINCT d.code) " +
        "FROM District d " +
        "WHERE (:provinceCode is null or d.provinceCode = :provinceCode)")
  int count(String provinceCode);
}
