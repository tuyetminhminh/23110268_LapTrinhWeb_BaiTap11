package vn.org.com.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Cấu hình PasswordEncoder (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Sử dụng BCrypt để mã hóa mật khẩu, phòng lỗi "There is no PasswordEncoder mapped"
        return new BCryptPasswordEncoder(); 
    }

    // 2. Cấu hình In-memory Users
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // Tạo user "cứng" (in-memory)
        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("123")) // Mật khẩu được mã hóa
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("123")) // Mật khẩu được mã hóa
                .roles("ADMIN", "USER") // User Admin có cả 2 quyền
                .build();

        // Sử dụng InMemoryUserDetailsManager để quản lý các user này
        return new InMemoryUserDetailsManager(user, admin);
    }

    // 3. Cấu hình SecurityFilterChain (Cấu hình phân quyền)
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   RoleBasedSuccessHandler successHandler) throws Exception {
      return http.csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/hello").permitAll()
              .requestMatchers("/admin/**").hasRole("ADMIN")
              .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
              .requestMatchers("/customer/**").authenticated()
              .anyRequest().authenticated())
          .formLogin(login -> login
              .successHandler(successHandler)  // dùng handler theo role
          )
          .logout(logout -> logout.logoutSuccessUrl("/hello"))
          .build();
    }

}