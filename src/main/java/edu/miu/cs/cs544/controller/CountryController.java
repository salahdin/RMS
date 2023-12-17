package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.repository.CountryRepository;
import edu.miu.cs.cs544.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

//    @GetMapping("/countries/{id}")
//    public ResponseEntity<?> getAllCountriesById(@PathVariable Long id) {
//        return countryRepository.findAllByCountryId(id)
//                .get(country -> ResponseEntity.ok().body(country))
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/countries")
    public ResponseEntity<?> getAllCountries() {
        return ResponseEntity.ok().body(countryRepository.findAll());
    }

    @PostMapping("/countries/{id}")
    public ResponseEntity<Country> createCountry(@RequestBody Country reservation) {
        Country createdReservation = countryService.createCountry(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }
}
