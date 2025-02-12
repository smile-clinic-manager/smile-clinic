package com.smile.clinic.smile_clinic.domain.models;

import com.smile.clinic.smile_clinic.domain.models.invitations.Invitation;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {

    private Long id;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String email;
    private String image;

    // Relationships
    private List<Treatment> treatments;
    private List<Invitation> invitations;

}
