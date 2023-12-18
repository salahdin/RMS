package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.dto.CountryDTO;
import edu.miu.cs.cs544.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public ResponseEntity<?> addCountry(@RequestBody Country country){
        return new ResponseEntity<Country>(countryService.addCountry(country), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<?> addCountry(CountryDTO countryDTO){
//        return new ResponseEntity<CountryDTO>(countryService.addCountry(countryDTO), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<?> getAllCountries(){
        return new ResponseEntity<List<CountryDTO>>( countryService.findAllCountries(), HttpStatus.OK);
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
