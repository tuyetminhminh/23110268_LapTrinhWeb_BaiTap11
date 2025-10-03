package vn.org.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import vn.org.com.repository.UserInfoRepository;
import vn.org.com.service.UserInfoDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    // Inject Service mới vào đây
    @Autowired
    private UserInfoDetailService userDetailsService; 

    // 1. Cấu hình PasswordEncoder (Giữ nguyên)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }

    // *KHÔNG CẦN BƯỚC 2 (In-memory users) NỮA*
    // Spring Security sẽ tự tìm UserDetailsService (UserInfoDetailService)
    // khi bạn khai báo Bean sau:
    
    // 2. Cấu hình AuthenticationProvider (Sử dụng Service và Encoder)
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // Sử dụng Service custom
        authProvider.setPasswordEncoder(passwordEncoder()); // Sử dụng Encoder
        return authProvider;
    }
    
    // 3. Cấu hình SecurityFilterChain (Giữ nguyên)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/user/new").permitAll() 
                .requestMatchers("/").permitAll()
                .requestMatchers("/customer/**").authenticated()
                .anyRequest().authenticated() 
            )
            .formLogin(formLogin -> formLogin
                .defaultSuccessUrl("/", true) // Dùng Giải pháp 1 ở câu trả lời trước
                .permitAll() 
            )
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }
}