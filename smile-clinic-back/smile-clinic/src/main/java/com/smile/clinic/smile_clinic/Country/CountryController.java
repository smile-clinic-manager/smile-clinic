package com.smile.clinic.smile_clinic.Country;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {

    private CountryRepository countryRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> countries = this.countryRepository.findAll();

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

}
