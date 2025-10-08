package com.smile.clinic.smile_clinic.domain.models.invitations;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invitation {

    private Long id;
    private InvitationStatus status;

    // Relationships
    private Clinic clinic;
    private User user;

}
