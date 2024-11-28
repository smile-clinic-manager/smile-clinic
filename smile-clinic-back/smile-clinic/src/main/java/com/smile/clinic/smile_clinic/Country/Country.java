package com.smile.clinic.smile_clinic.Country;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "countries")
@Data
public class Country {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

   //Prueba commit -sonarqube
    @NotNull
    String name;

}
