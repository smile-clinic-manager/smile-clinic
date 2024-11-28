package com.smile.clinic.smile_clinic.infrastructure.adapters.config.security.filters;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.UserEntityRepository;
import com.smile.clinic.smile_clinic.utils.JwtAdapter;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtAdapter jwtAdapter;
    private UserEntityRepository userEntityRepository;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Obtener encabezado llamado Authorization
        String jwt = request.getHeader("Authorization"); //Incluir esta cabecera en todas las peticiones HTTP

        if (!StringUtils.hasText(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Obtener el username, validar el token, su formato, la firma y la fecha de expiración.
        String username = jwtAdapter.getAllTokenClaims(jwt).get("username", String.class);

        // 3. Settear objeto autenticación dentro de security context holder
        UserEntity user = userEntityRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found. Username " + username));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );
        // Añadimos info del request a la autenticación del context
        authToken.setDetails(new WebAuthenticationDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 4. Ejecutar el registro de filtros
        filterChain.doFilter(request, response);
    }
}
