package com.haunt.job_seeking.security;

import com.haunt.job_seeking.security.error.UnAuthenticationCustomHandler;
import com.haunt.job_seeking.filter.JwtAuthenticationFilter;
import com.haunt.job_seeking.security.error.UnAuthorizationCustomHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final UnAuthenticationCustomHandler unAuthenticationCustomHandler;
  private final UnAuthorizationCustomHandler unAuthorizationCustomHandler;

  @Bean
  public SecurityFilterChain securityFilterChainUsersAPI(HttpSecurity httpSecurity) throws Exception {
    sharedSecurityConfiguration(httpSecurity);
    httpSecurity
          .securityMatcher("/api/v1/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/**")
          .authorizeHttpRequests(auth -> {
            auth.anyRequest().permitAll();
          })
          .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
          .exceptionHandling(exception -> exception
                .authenticationEntryPoint(unAuthenticationCustomHandler)
                .accessDeniedHandler(unAuthorizationCustomHandler));
    return httpSecurity.build();
  }

  @Bean
  public SecurityFilterChain securityFilterChainAdminsAPI(HttpSecurity httpSecurity) throws Exception {
    sharedSecurityConfiguration(httpSecurity);
    httpSecurity
          .securityMatcher("/api/v1/admin/**")
          .authorizeHttpRequests(auth -> {
            auth.anyRequest().authenticated();
          })
          .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
          .exceptionHandling(exception -> exception
                .authenticationEntryPoint(unAuthenticationCustomHandler)
                .accessDeniedHandler(unAuthorizationCustomHandler));

    return httpSecurity.build();
  }

  private void sharedSecurityConfiguration(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
          .csrf(AbstractHttpConfigurer::disable)
          .cors(AbstractHttpConfigurer::disable)
          .sessionManagement(httpSecuritySessionManagementConfigurer -> {
            httpSecuritySessionManagementConfigurer
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
          });
  }


  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedHeader("*");
    configuration.addAllowedOrigin("*");
    configuration.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
