package com.typemoon.config;//package com.typemoon.config;

import com.typemoon.filter.JwtAuthenticationTokenFilter;
import com.typemoon.handler.AccessDecisionManagerImpl;
import com.typemoon.handler.FilterInvocationSecurityMetadataSourceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

import java.io.IOException;

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

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;


    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new FilterInvocationSecurityMetadataSourceImpl();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new AccessDecisionManagerImpl();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(formLogin -> {
            formLogin.loginProcessingUrl("users/login")
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler);

//            formLogin.loginProcessingUrl("users/login")
//                    .successHandler(new AuthenticationSuccessHandler() {
//
//                        @Override
//                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                            response.setContentType("text/html;charset=utf-8");
//                            response.getWriter().write("LoginOK");
//
//                            System.out.println("authentication.getCredentials():" + authentication.getCredentials());
//                            System.out.println("authentication.getPrincipal():" + authentication.getPrincipal());
//                            System.out.println("authentication.getAuthorities():" + authentication.getAuthorities());
//
//                        }
//                    })
//                    .failureHandler(new AuthenticationFailureHandler() {
//                        @Override
//                        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                            response.setContentType("text/html;charset=utf-8");
//                            response.getWriter().write("LoginError");
//                            System.out.println("登录异常信息：");
//                            exception.printStackTrace();
//                        }
//                    });

//            formLogin.successHandler(authenticationSuccessHandler)
//                    .failureHandler(authenticationFailureHandler);
        });
        http.authorizeHttpRequests((authorize) -> {
            authorize.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                @Override
                public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                    fsi.setSecurityMetadataSource(securityMetadataSource());
                    fsi.setAccessDecisionManager(accessDecisionManager());
                    return fsi;
                }
            });
            authorize.anyRequest().permitAll();
//            authorize.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
//            authorize.requestMatchers("/login").permitAll();

        });

        http.csrf(Customizer.withDefaults())
                .cors(Customizer.withDefaults())
                .exceptionHandling(exceptionHandler -> {
                    exceptionHandler.authenticationEntryPoint(authenticationEntryPoint)
                            .accessDeniedHandler(accessDeniedHandler);
                })
                .sessionManagement(sessionManagement -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                });

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
