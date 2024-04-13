package com.haunt.job_seeking.configuration.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

import static com.haunt.job_seeking.constants.JobSeekingConstants.AuditorConstant.ANONYMOUS;
import static com.haunt.job_seeking.constants.JobSeekingConstants.AuditorConstant.SYSTEM;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (Objects.nonNull(authentication) && !isAnonymous()) {
      return Optional.ofNullable(authentication.getPrincipal().toString());
    }
    return Optional.of(SYSTEM);
  }

  private boolean isAnonymous() {
    return SecurityContextHolder.getContext().getAuthentication().getName().equals(ANONYMOUS);
  }
}
