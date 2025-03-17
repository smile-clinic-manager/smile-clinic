package com.smile.clinic.smile_clinic.application.ports.output;

import java.util.List;

public interface UserClinicRolePersistancePort {
    void createUserClinicRole(Long userId, Long clinicId, Long roleId);
    boolean recordExists(Long userId, Long clinicId, Long roleId);
}
