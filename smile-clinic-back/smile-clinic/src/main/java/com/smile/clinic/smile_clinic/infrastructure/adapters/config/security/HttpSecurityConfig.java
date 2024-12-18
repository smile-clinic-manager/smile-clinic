package com.smile.clinic.smile_clinic.infrastructure.adapters.config.security;

import com.smile.clinic.smile_clinic.domain.models.users.roles.Role;
import com.smile.clinic.smile_clinic.infrastructure.adapters.config.security.filters.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class HttpSecurityConfig {

    private AuthenticationProvider authenticationProvider;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) //Allow h2-console to be rendered
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement( sessionManagerConfig -> sessionManagerConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authRequestConfig -> {
                authRequestConfig.requestMatchers(HttpMethod.POST, "/users/register").permitAll();
                authRequestConfig.requestMatchers(HttpMethod.GET, "/auth/**").permitAll();
                authRequestConfig.requestMatchers("/h2-console/**").permitAll();
                authRequestConfig.requestMatchers(HttpMethod.GET,"/users/profile").hasAnyRole(Role.CLINIC_ADMIN.name()); //Autorización por roles

                // Other routes not defined above require the user to be authenticated
                authRequestConfig.anyRequest().authenticated();
            })
            .build();
    }

}
