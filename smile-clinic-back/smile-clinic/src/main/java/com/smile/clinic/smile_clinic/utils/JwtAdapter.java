package com.smile.clinic.smile_clinic.utils;

import com.smile.clinic.smile_clinic.application.ports.output.TokenProviderPort;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.ClinicRoleDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserClinicRoleEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Slf4j
@Component
public class JwtAdapter implements TokenProviderPort {

    @Value("${security.jwt.expiration-minutes}")
    private Long EXPIRATION_MINUTES;
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    public String generateToken(User user) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + EXPIRATION_MINUTES * 60 * 1000);

        SecretKey secretKey = generateKey();

        String token = Jwts.builder()
            .subject(user.getUsername())
            .issuedAt(issuedAt)
            .expiration(expiration)
            .claims(generateExtraClaims(user))
            .signWith(secretKey)
            .compact();
        return token;
    }

    private Map<String,Object> generateExtraClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName1", user.getLastName1());
        claims.put("lastName2", user.getLastName2());
        claims.put("roles", mapClinicRoles(user));

        log.info(claims.get("roles").toString());

        return claims;
    }

    private List<ClinicRoleDTO> mapClinicRoles(User user){
        List<ClinicRoleDTO> clinicRoles = new ArrayList<>();
        List<UserClinicRoleEntity> userClinicRoles = user.getUserClinicRoles();
        for(UserClinicRoleEntity clinicRoleEntity : userClinicRoles){
            ClinicRoleDTO clinicRole = ClinicRoleDTO.builder()
                    .clinic(clinicRoleEntity.getClinic().getName())
                    .role(clinicRoleEntity.getRole().getName())
                    .build();
            clinicRoles.add(clinicRole);
        }
        return clinicRoles;
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
