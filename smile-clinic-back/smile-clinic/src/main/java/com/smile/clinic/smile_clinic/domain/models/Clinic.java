package com.smile.clinic.smile_clinic.domain.models;

import com.smile.clinic.smile_clinic.domain.models.users.User;
import lombok.*;

import java.util.List;

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

    // Relations
    private User owner; //User owner of the clinic
    private List<User> employees; // Employees of the clinic (including the owner)

}
