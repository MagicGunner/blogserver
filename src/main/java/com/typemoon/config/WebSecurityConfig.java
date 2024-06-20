package com.typemoon.config;//package com.typemoon.config;

import com.typemoon.filter.JwtAuthenticationTokenFilter;
import com.typemoon.handler.AccessDecisionManagerImpl;
import com.typemoon.handler.FilterInvocationSecurityMetadataSourceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new FilterInvocationSecurityMetadataSourceImpl();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new AccessDecisionManagerImpl();
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            authorize.requestMatchers("/login").permitAll();
            authorize.anyRequest().authenticated();
//            authorize.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                @Override
//                public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
//                    fsi.setSecurityMetadataSource(securityMetadataSource());
//                    fsi.setAccessDecisionManager(accessDecisionManager());
//                    return fsi;
//                }
//            });
        });

        http.formLogin(formLogin -> {
            formLogin.successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler);
        });

//        http.formLogin(formLogin -> {
//            formLogin.loginProcessingUrl("/users/login")
//                    .successHandler(authenticationSuccessHandler)
//                    .failureHandler(authenticationFailureHandler);
//        });

        http.csrf(Customizer.withDefaults())
                .cors(Customizer.withDefaults());
//                .exceptionHandling(exceptionHandler -> {
//                    exceptionHandler.authenticationEntryPoint(authenticationEntryPoint)
//                            .accessDeniedHandler(accessDeniedHandler);
//                })
//                .sessionManagement(sessionManagement -> {
//                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                });

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
