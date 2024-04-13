package com.haunt.job_seeking.repository.address;

import com.haunt.job_seeking.dto.response.address.province.ProvinceInfoResponse;
import com.haunt.job_seeking.dto.response.address.province.ProvinceResponse;
import com.haunt.job_seeking.entity.address.Province;
import com.haunt.job_seeking.repository.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvinceRepository extends BaseRepository<Province> {
  boolean existsByCode(String code);

  @Query("SELECT new com.haunt.job_seeking.dto.response.address.province.ProvinceInfoResponse " +
        "(p.name, p.nameEn, p.codeName, p.code) " +
        "FROM Province p " +
        "WHERE p.code = :code")
  ProvinceInfoResponse get(String code);

  @Query("SELECT new com.haunt.job_seeking.dto.response.address.province.ProvinceResponse " +
        "(p.code, p.name, p.nameEn, p.fullName, p.fullNameEn, p.codeName) " +
        "FROM Province p " +
        "WHERE :keyword is null or ( " +
        "lower( p.name) LIKE :keyword% OR " +
        "lower( p.nameEn) LIKE :keyword% OR " +
        "lower( p.fullName) LIKE :keyword% OR " +
        "lower( p.fullNameEn) LIKE :keyword% OR " +
        "lower( p.codeName) LIKE :keyword%) " +
        "ORDER BY p.name")
  List<ProvinceResponse> search(String keyword, Pageable pageable);

  @Query("SELECT new com.haunt.job_seeking.dto.response.address.province.ProvinceResponse " +
        "(p.code, p.name, p.nameEn, p.fullName, p.fullNameEn, p.codeName) " +
        "FROM Province p " +
        "ORDER BY p.name")
  List<ProvinceResponse> list();

  @Query("SELECT count(DISTINCT p.code) " +
        "FROM Province p " +
        "WHERE :keyword is null or ( " +
        "lower( p.name) LIKE :keyword% OR " +
        "lower( p.nameEn) LIKE :keyword% OR " +
        "lower( p.fullName)  LIKE :keyword% OR " +
        "lower( p.fullNameEn) LIKE :keyword% OR " +
        "lower( p.codeName) LIKE :keyword%)")
  int countSearch(String keyword);
}
