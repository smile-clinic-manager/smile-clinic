package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.RoleServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.RolePersistancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleServicePort {
    private final RolePersistancePort rolePersistancePort;

}
