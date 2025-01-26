package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTO {
    private String errorMessage;
}
