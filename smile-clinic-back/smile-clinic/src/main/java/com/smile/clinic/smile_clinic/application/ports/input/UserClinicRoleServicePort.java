package com.smile.clinic.smile_clinic.application.ports.input;

import java.util.List;

public interface UserClinicRoleServicePort {

    void createUserClinicRole(Long userId, Long clinicId, Long roleId) throws Exception;
    void createMultipleUserClinicRole(Long userId, Long clinicId, List<Long> roleIds) throws Exception;

    void deleteUserClinicRole(Long clinicId, Long userId);
}
