package com.authenticator.microservice.infrastructure.configs;

import com.authenticator.microservice.infrastructure.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityCongfiguration {
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityCongfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                        "/api/user/register",
                                        "/api/user/register**",
                                        "/api/user/login",
                                        "/api/user/login**"
                                ).permitAll()
                                .requestMatchers(
                                        "/swagger-ui/index.html",
                                        "/swagger-ui/index.html/**",
                                        "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/api-docs",
                                        "/api-docs/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources/**",
                                        "/webjars/**",
                                        "/swagger-ui-custom.html"
                                ).permitAll()
                                .anyRequest().authenticated()
                                //.anyRequest().permitAll()
                ).sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(
                        jwtFilter, UsernamePasswordAuthenticationFilter.class
                ).formLogin(
                        login -> login
                                .defaultSuccessUrl("/")
                                .permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
