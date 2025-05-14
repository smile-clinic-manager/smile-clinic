package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import com.smile.clinic.smile_clinic.domain.models.users.DentistData;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordEntryDTO implements Serializable {

    @NotBlank(message = "Field userId cannot be empty, blank or null")
    private int userId;

    @NotBlank(message = "Field dateTime cannot be empty, blank or null")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}$", message = "Field dateTime must have the format yyyy-MM-dd HH:mm")
    private String dateTime;

    @NotNull(message = "Field notes cannot be null")
    private String observations;

    @NotNull(message = "Field user cannot be null")
    private DentistData user;

    private TreatmentInstance treatmentInstance;

}
