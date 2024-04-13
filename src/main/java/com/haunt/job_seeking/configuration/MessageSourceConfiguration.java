package com.haunt.job_seeking.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import static com.haunt.job_seeking.constants.JobSeekingConstants.CommonConstants.ENCODING_UTF_8;

@Configuration
public class MessageSourceConfiguration {

  @Bean
  public MessageSource messageSource() {
    var messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:i18n/messages");
    messageSource.setDefaultEncoding(ENCODING_UTF_8);
    return messageSource;
  }

}
