package com.haunt.job_seeking.filter;

import com.haunt.job_seeking.exception.token.TokenExpiredException;
import com.haunt.job_seeking.exception.token.TokenInvalidException;
import com.haunt.job_seeking.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import static com.haunt.job_seeking.constants.JobSeekingConstants.AuthConstant.*;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  @Override
  protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
  ) throws ServletException, IOException {
    log.debug(
          "(doFilterInternal)request: {}, response: {}, filterChain: {}",
          request,
          response,
          filterChain
    );

    String accessToken = request.getHeader(AUTHORIZATION);
    if (Objects.isNull(accessToken)) {
      filterChain.doFilter(request, response);
      return;
    } else if (!accessToken.startsWith(TYPE_TOKEN)) {
      filterChain.doFilter(request, response);
      return;
    }

    var jwtToken = accessToken.substring(AUTHORIZATION_TYPE_SIZE);

    try {
      var userid = jwtService.getSubjectFromToken(jwtToken);

      var username = jwtService.getUsernameFromToken(jwtToken);

      var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            userid, username
      );
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

    } catch (TokenInvalidException e) {
      response.sendError(HttpStatus.UNAUTHORIZED.value(), INVALID_TOKEN);
    } catch (TokenExpiredException e) {
      response.sendError(HttpStatus.UNAUTHORIZED.value(), EXPIRED_TOKEN);
    }

    filterChain.doFilter(request, response);
  }
}
