package com.ecommerce.demo.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authException) throws IOException, IOException {

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.getWriter().write("{ \"message\": \"" + authException.getMessage() + "\" }");
  }

  @Override
  public void afterPropertiesSet() {
    setRealmName("JWT Authentication");
    super.afterPropertiesSet();
  }
}