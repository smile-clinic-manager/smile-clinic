package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.domain.models.users.Role;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.TreatmentDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;

import java.util.List;

public interface UserClinicRoleServicePort {

    void createUserClinicRole(Long userId, Long clinicId, Long roleId) throws Exception;
    void createMultipleUserClinicRole(Long userId, Long clinicId, List<Long> roleIds) throws Exception;

    void deleteUserClinicRole(Long clinicId, Long userId);

    void updateUserClinicRole(User user, Long clinicId, List<Role> roles);
}
