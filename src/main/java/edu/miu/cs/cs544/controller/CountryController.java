package edu.miu.cs.cs544.controller;

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
    public ResponseEntity<?> addLocation(CountryDTO locationDTO){
        return new ResponseEntity<CountryDTO>(countryService.addCountry(locationDTO), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllLocation(){
        return new ResponseEntity<List<CountryDTO>>( countryService.findAllCountries(), HttpStatus.OK);
    }
    @GetMapping("/{country_id}")
    public ResponseEntity<?> getLocation(@PathVariable Long country_id){
        return new ResponseEntity<CountryDTO>(countryService.findById(country_id), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> updateLocation(@Valid @RequestBody CountryDTO locationDTO){
        return new ResponseEntity<CountryDTO>(countryService.updateCountry(locationDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{country_id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long location_id){
        return new ResponseEntity<String>(countryService.deleteById(location_id), HttpStatus.OK);
    }
}
