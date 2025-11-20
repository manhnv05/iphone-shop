package com.example.mobile_shop.config;

import com.example.mobile_shop.entity.QuyenHan;
import com.example.mobile_shop.entity.TaiKhoan;
import com.example.mobile_shop.repository.tai_khoan.TaiKhoanRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @org.springframework.beans.factory.annotation.Value("${app.frontend.base-url:http://localhost:3000}")
    private String frontendBaseUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   com.example.mobile_shop.security.oauth2.CustomOAuth2UserService customOAuth2UserService) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        // 1. Public endpoints (no authentication)
                        .requestMatchers(
                                "/tai-khoan/login",
                                "/tai-khoan/requestOtp",
                                "/tai-khoan/requestOtpMk",
                                "/tai-khoan/verifyOtp",
                                "/tai-khoan/addTk",
                                "/tai-khoan/update-tk",
                                // OAuth2 endpoints
                                "/oauth2/**",
                                "/login/oauth2/**"
                        ).permitAll()

                        // Allow public GET access to view products
                        .requestMatchers(HttpMethod.GET,
                                "/san-pham/get-all",
                                "/san-pham/get-one/**",
                                "/chi-tiet-san-pham/get-all-by-idsp/**"
                        ).permitAll()

                        // 2. Endpoints that any authenticated user (customer/staff/admin) can access
                        // Placed BEFORE the admin catch-all so customers can access /dia-chi/**
                        .requestMatchers("/dia-chi/**").authenticated()
                        // Session helper endpoints for FE
                        .requestMatchers("/tai-khoan/me", "/tai-khoan/logout").authenticated()

                        // 3. STAFF and ADMIN access to certain management endpoints
                        .requestMatchers(
                                "/ban-hang/**",
                                "/hoa-don/**",
                                "/thong-ke/**",
                                "/phieu-bao-hanh/**",
                                "/lich-su-bao-hanh/**",
                                "/san-pham/**"
                        ).hasAnyRole("ADMIN", "STAFF")

                        // 4. ADMIN only for everything else (admin should be able to access all forms/APIs)
                        .anyRequest().hasRole("ADMIN")
                )
                // OAuth2 Login (Google)
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                        .successHandler((request, response, authentication) -> {
                            // On success, just redirect FE to success page, FE will call /tai-khoan/me to fetch user
                            String redirect = frontendBaseUrl + "/oauth2/success";
                            response.sendRedirect(redirect);
                        })
                        .failureHandler((request, response, exception) -> {
                            String redirect = frontendBaseUrl + "/oauth2/failure";
                            response.sendRedirect(redirect);
                        })
                )
                // Use IF_REQUIRED for session; switch to STATELESS if using JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService(TaiKhoanRepository taiKhoanRepository) {
        return username -> {
            TaiKhoan tk = taiKhoanRepository.findByTenDangNhap(username);
            if (tk == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }

            // Map role stored in QuyenHan.ma to Spring role name (without "ROLE_" prefix).
            // User.withUsername(...).roles(...) will add the "ROLE_" prefix automatically.
            QuyenHan quyenHan = tk.getIdQuyenHan();
            String role = "CUSTOMER"; // default role if none
            if (quyenHan != null && quyenHan.getMa() != null && !quyenHan.getMa().isEmpty()) {
                role = quyenHan.getMa().replaceFirst("^ROLE_", ""); // ensure no "ROLE_" prefix
            }

            return User.withUsername(tk.getTenDangNhap())
                    .password(tk.getMatKhau())
                    .roles(role)
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}