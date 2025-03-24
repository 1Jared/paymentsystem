package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) 
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/payments/**").authenticated() // Secure the payments API
                        .anyRequest().permitAll() // Allow all other requests
                )
                .httpBasic(); // Use basic authentication (or configure OAuth2 as needed)

        return http.build();
    }
}