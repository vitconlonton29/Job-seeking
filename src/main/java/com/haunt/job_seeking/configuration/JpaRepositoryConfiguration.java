package com.haunt.job_seeking.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.haunt.job_seeking"
)
public class JpaRepositoryConfiguration {
}
