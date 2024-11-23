package com.smile.clinic.smile_clinic.infrastructure.adapters.config.security;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class SecurityBeansInjector {

    private UserEntityRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Responsable de autenticar las credenciales de los usuarios
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //Encriptamos las contraseñas con algoritmo BCrypt
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return (username -> {
            return this.userRepository.findUserByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("No user found with username " + username));
        });
    }
}