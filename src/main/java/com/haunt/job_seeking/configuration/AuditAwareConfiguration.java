package com.haunt.job_seeking.configuration;

import com.haunt.job_seeking.configuration.auditor.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditAwareConfiguration {
  @Bean
  public AuditorAware<String> auditorProvider() {return new AuditorAwareImpl();}
}
