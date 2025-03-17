package com.smile.clinic.smile_clinic.application.ports.input;

import java.util.List;

public interface UserClinicRoleServicePort {
    void createMultipleUserClinicRole(Long userId, Long clinicId, List<Long> roleIds) throws Exception;
}
