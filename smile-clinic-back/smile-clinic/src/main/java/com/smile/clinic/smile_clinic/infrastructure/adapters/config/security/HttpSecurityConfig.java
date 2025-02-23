package com.smile.clinic.smile_clinic.infrastructure.adapters.config.security;

import com.smile.clinic.smile_clinic.infrastructure.adapters.config.security.filters.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class HttpSecurityConfig {

    private AuthenticationProvider authenticationProvider;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity
                .cors(Customizer.withDefaults())
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) //Allow h2-console to be rendered
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement( sessionManagerConfig -> sessionManagerConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authRequestConfig -> {
                    authRequestConfig.requestMatchers(HttpMethod.POST, "/users/signup").permitAll();
                    authRequestConfig.requestMatchers(HttpMethod.POST, "/users/signup").permitAll();
                    authRequestConfig.requestMatchers("/auth/**").permitAll();
                    authRequestConfig.requestMatchers("/h2-console/**").permitAll();
                    authRequestConfig.requestMatchers("/error/**").permitAll();
                    authRequestConfig.requestMatchers(HttpMethod.GET,"/users/profile").hasAuthority("MANAGE_USERS"); //Autorización por roles
                    // Other routes not defined above require the user to be authenticated
                    authRequestConfig.anyRequest().authenticated();
            })
            .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
                System.out.println("CORS configuration applied");
            }
        };
    }

}
