package com.typemoon.config;//package com.typemoon.config;

import lombok.val;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
//        val staticResourceRequestMatcher = PathRequest.toStaticResources().atCommonLocations();
        http.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            authorize.anyRequest().authenticated();
        });
        http.formLogin();
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
