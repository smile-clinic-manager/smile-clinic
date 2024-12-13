package com.smile.clinic.smile_clinic.domain.models;

import lombok.*;

import java.time.Duration;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Treatment {
    private String identifier;
    private String name;
    private Double price;
    private Duration duration;
    private String notes;
}
