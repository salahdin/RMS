package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.dto.CountryDTO;
import edu.miu.cs.cs544.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public ResponseEntity<?> addCountry(@RequestBody CountryDTO countryDTO){
        CountryDTO country = countryService.addCountry(countryDTO);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCountries(){
        return new ResponseEntity<List<CountryDTO>>(countryService.findAllCountries(), HttpStatus.OK);
    }

    @GetMapping("/{country_id}")
    public ResponseEntity<?> getCountry(@PathVariable Long country_id){
        return new ResponseEntity<CountryDTO>(countryService.findById(country_id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCountry(@Valid @RequestBody CountryDTO countryDTO){
        return new ResponseEntity<CountryDTO>(countryService.updateCountry(countryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{country_id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long country_id){
        return new ResponseEntity<String>(countryService.deleteById(country_id), HttpStatus.OK);
    }
}
