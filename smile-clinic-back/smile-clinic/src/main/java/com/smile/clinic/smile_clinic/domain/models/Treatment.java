package com.smile.clinic.smile_clinic.domain.models;

import lombok.*;

import java.time.Duration;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Treatment {
    private Long id;
    private String name;
    //private Double price; **COMMENTED UNTIL WE KNOW HOW BILLING SHOULD BE HANDLED IF ANY
}
