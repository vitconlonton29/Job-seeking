package com.haunt.job_seeking.repository.address;

import com.haunt.job_seeking.dto.response.address.AddressResponse;
import com.haunt.job_seeking.entity.address.Address;
import com.haunt.job_seeking.repository.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<Address> {
  @Query("select a from Address a")
  List<Address> search(Pageable pageable, String name);

  @Query("select count(a) from Address a ")
  int countSearch(String name);

  @Modifying
  @Query("update Address a set a.isDeleted = true where a.id = :id")
  void softDelete(String id);

  @Query("select new com.haunt.job_seeking.dto.response.address.AddressResponse (" +
        " a.id, w.name, w.nameEn, w.codeName, d.name, d.nameEn, d.codeName, p.name, p.nameEn, p.codeName, a.detail ) " +
        " FROM Address a " +
        " LEFT JOIN Province p ON p.code = a.provinceCode" +
        " LEFT JOIN District d ON d.code = a.districtCode" +
        " LEFT JOIN Ward  w on w.code = a.wardCode" +
        " WHERE a.id = :id")
  AddressResponse retrieve(String id);
}
