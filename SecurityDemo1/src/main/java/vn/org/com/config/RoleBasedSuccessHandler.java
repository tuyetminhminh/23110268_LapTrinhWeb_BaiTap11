package vn.org.com.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoleBasedSuccessHandler implements AuthenticationSuccessHandler {
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
    String redirectUrl = "/user/home"; // mặc định

    for (GrantedAuthority auth : authentication.getAuthorities()) {
      String role = auth.getAuthority();
      if ("ROLE_ADMIN".equals(role)) {
        redirectUrl = "/admin/home";
        break;
      } else if ("ROLE_USER".equals(role)) {
        redirectUrl = "/user/home";
      }
    }
    response.sendRedirect(redirectUrl);
  }
}
