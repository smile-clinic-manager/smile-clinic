package com.smile.clinic.smile_clinic.infrastructure.adapters.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class HttpSecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) //Allow h2-console to be rendered
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement( sessionManagerConfig -> sessionManagerConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .authorizeHttpRequests(authRequestConfig -> {
                authRequestConfig.requestMatchers(HttpMethod.POST, "/users/register").permitAll();
                authRequestConfig.requestMatchers(HttpMethod.GET, "/auth/**").permitAll();
                authRequestConfig.requestMatchers("/h2-console/**").permitAll();

                // Other routes not defined above require the user to be authenticated
                authRequestConfig.anyRequest().authenticated();
            })
            .build();
    }

}
