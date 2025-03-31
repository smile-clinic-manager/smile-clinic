package com.smile.clinic.smile_clinic.utils;

import com.smile.clinic.smile_clinic.application.ports.output.TokenProviderPort;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.domain.models.users.UserClinicRole;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.ClinicRoleDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.RoleDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserClinicRoleEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAdapter implements TokenProviderPort {

    @Value("${security.jwt.expiration-minutes}")
    private Long EXPIRATION_MINUTES;
    @Value("${security.refresh-jwt.expiration-minutes}")
    private Long REFRESH_TOKEN_EXPIRATION;
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    @Override
    public String generateRefreshToken(User user) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + EXPIRATION_MINUTES * 60 * 1000);
        SecretKey secretKey = generateKey();

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String generateToken(User user) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + EXPIRATION_MINUTES * 60 * 1000);

        SecretKey secretKey = generateKey();

        return Jwts.builder()
            .subject(user.getUsername())
            .issuedAt(issuedAt)
            .expiration(expiration)
            .claims(generateExtraClaims(user))
            .signWith(secretKey)
            .compact();
    }

    private Map<String,Object> generateExtraClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("dni", user.getDni());
        claims.put("username", user.getUsername());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName1", user.getLastName1());
        claims.put("lastName2", user.getLastName2());
        claims.put("roles", mapClinicRoles(user));

        log.info(claims.get("roles").toString());

        return claims;
    }

    private List<ClinicRoleDTO> mapClinicRoles(User user){
        List<UserClinicRoleEntity> userClinicRoles = user.getUserClinicRoles();
        List<ClinicRoleDTO> ls = parseToClinicRoleDTO(userClinicRoles);
        return ls;
    }

    private List<ClinicRoleDTO> parseToClinicRoleDTO(List<UserClinicRoleEntity> userClinicRoles) {
        Map<Long, ClinicRoleDTO> clinicRoleMap = new HashMap<>();

        for (UserClinicRoleEntity userClinicRole : userClinicRoles) {
            Long clinicId = userClinicRole.getClinic().getId();
            String clinicName = userClinicRole.getClinic().getName();

            ClinicRoleDTO clinicRoleDTO = clinicRoleMap.get(clinicId);

            // Create nuevo DTO si no existe la clinica
            if (clinicRoleDTO == null) {
                clinicRoleDTO = new ClinicRoleDTO();
                clinicRoleDTO.setClinicId(clinicId);
                clinicRoleDTO.setClinic(clinicName);
                clinicRoleDTO.setRoles(new ArrayList<>());
                clinicRoleMap.put(clinicId, clinicRoleDTO);
            }

            // Añadir roles
            RoleDTO roleDTO = new RoleDTO(userClinicRole.getRole().getId(), userClinicRole.getRole().getName());
            clinicRoleDTO.getRoles().add(roleDTO);
        }

        return new ArrayList<>(clinicRoleMap.values());
    }

    @Override
    public SecretKey generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    // Obtain all claims from a JWToken
    public Claims getAllTokenClaims(String token) {
        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
    }
}
