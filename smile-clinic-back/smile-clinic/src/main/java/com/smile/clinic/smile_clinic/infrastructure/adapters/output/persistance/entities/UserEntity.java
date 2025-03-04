package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
@Builder
@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName1;

    private String lastName2;

    @NotBlank
    @Column(unique = true)
    @Pattern(regexp = "^\\d{8}[A-Z]$")
    private String dni;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    @Length(min = 5, max = 25)
    private String username;

    @NotBlank
    private String password;

    //Citas
    @OneToMany(mappedBy = "user")
    private List<AppointmentEntity> appointments;

    //Relaciones con clínicas y su rol asociado
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserClinicRoleEntity> userClinicRoles;

    // UserDetails methods (security & authentication methods)
    @Override
    @Transactional
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(userClinicRoles == null || userClinicRoles.isEmpty()) return null;

        Set<SimpleGrantedAuthority> authorities = userClinicRoles.stream()
                .map(UserClinicRoleEntity::getRole)
                .filter(Objects::nonNull)  //Prevent null roles
                .flatMap(role -> role.getPermissions().stream())
                .map(PermissionEntity::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        log.info(authorities.toString()); // AAAAAAAAAAAAAAAAAAAAAAA
        return authorities;
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
