package com.smile.clinic.smile_clinic.domain.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    private Long id;
    private int ownerId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String description;
    private String image;
}
