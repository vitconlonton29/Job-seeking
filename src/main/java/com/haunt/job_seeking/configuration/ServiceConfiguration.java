package com.haunt.job_seeking.configuration;

import com.haunt.job_seeking.facade.AddressFacadeService;
import com.haunt.job_seeking.facade.impl.AddressFacadeServiceImpl;
import com.haunt.job_seeking.repository.AccountRepository;
import com.haunt.job_seeking.repository.address.AddressRepository;
import com.haunt.job_seeking.repository.address.DistrictRepository;
import com.haunt.job_seeking.repository.address.ProvinceRepository;
import com.haunt.job_seeking.repository.address.WardRepository;
import com.haunt.job_seeking.service.AccountService;
import com.haunt.job_seeking.service.address.AddressService;
import com.haunt.job_seeking.service.address.DistrictService;
import com.haunt.job_seeking.service.address.ProvinceService;
import com.haunt.job_seeking.service.address.WardService;
import com.haunt.job_seeking.service.address.impl.AddressServiceImpl;
import com.haunt.job_seeking.service.address.impl.DistrictServiceImpl;
import com.haunt.job_seeking.service.address.impl.ProvinceServiceImpl;
import com.haunt.job_seeking.service.address.impl.WardServiceImpl;
import com.haunt.job_seeking.service.impl.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfiguration {
  @Bean
  public AccountService accountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
    return new AccountServiceImpl(accountRepository, passwordEncoder);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AddressService addressService(AddressRepository addressRepository) {
    return new AddressServiceImpl(addressRepository);
  }

  @Bean
  public ProvinceService provinceService(ProvinceRepository repository) {
    return new ProvinceServiceImpl(repository);
  }

  @Bean
  public DistrictService districtService(DistrictRepository repository) {
    return new DistrictServiceImpl(repository);
  }

  @Bean
  public WardService wardService(WardRepository repository) {
    return new WardServiceImpl(repository);
  }

  @Bean
  public AddressFacadeService addressFacadeService(
        AddressService addressService,
        DistrictService districtService,
        ProvinceService provinceService,
        WardService wardService
  ) {
    return new AddressFacadeServiceImpl(
          addressService,
          districtService,
          provinceService,
          wardService
    );
  }
}
