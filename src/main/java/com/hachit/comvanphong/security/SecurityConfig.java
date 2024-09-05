package com.hachit.comvanphong.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        // Cho phép truy cập Swagger mà không cần xác thực
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        // Cho phép truy cập không cần xác thực với login và register
                        .requestMatchers("/login", "/api/auth/login", "/api/auth/register").permitAll()
                        .anyRequest().authenticated()  // Các URL khác yêu cầu xác thực
                )
                // Cấu hình form login
                .formLogin(form -> form
                        .loginPage("/login")  // Đường dẫn đến trang login
                        .defaultSuccessUrl("/swagger-ui/index.html", true)  // Chuyển hướng đến Swagger sau khi đăng nhập thành công
                        .permitAll()  // Cho phép tất cả người dùng truy cập trang login
                )
                .logout(LogoutConfigurer::permitAll)
                .csrf(AbstractHttpConfigurer::disable);  // Tắt CSRF cho phát triển

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Mã hóa mật khẩu với BCrypt
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))  // Mã hóa mật khẩu "admin"
                .roles("ADMIN")  // Thiết lập role là ADMIN
                .build();
        return new InMemoryUserDetailsManager(adminUser);
    }
}