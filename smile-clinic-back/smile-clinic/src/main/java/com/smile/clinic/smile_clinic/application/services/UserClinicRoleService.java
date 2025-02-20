package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.UserClinicRoleServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.UserServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.UserClinicRolePersistancePort;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserClinicRoleService implements UserClinicRoleServicePort {
    private final UserClinicRolePersistancePort userClinicRolePersistancePort;
}
