package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.adapter.CountryAdapter;
import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.dto.CountryDTO;
import edu.miu.cs.cs544.repository.CountryRepository;
import edu.miu.cs.cs544.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private CountryAdapter countryAdapter;

    private CountryRepository countryRepository;

    @Override
    public CountryDTO addCountry(CountryDTO countryDTO) {
        try {
            countryRepository.save(countryAdapter.dtoToEntity(countryDTO));
            return countryDTO;
        }catch (RuntimeException e){
            throw new RuntimeException("Failed to add this country");
        }
    }

    @Override
    public Country addCountry(Country country) {
        try {
            countryRepository.save(country);
            return  country;
        }catch (RuntimeException e){
            throw new RuntimeException("Failed to add this country");
        }
    }
    @Override
    public List<CountryDTO> findAllCountries() {
        return countryAdapter.entityToDtoAll(countryRepository.findAll());
    }
    @Override
    public CountryDTO findById(Long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        Country country = countryOptional.orElseThrow(() -> new EntityNotFoundException("Location with id " + id + " not found"));
        return countryAdapter.entityToDto(country);
    }
    @Override
    public CountryDTO updateCountry(CountryDTO locationDTO) {
        try {
            countryRepository.save(countryAdapter.dtoToEntity(locationDTO));
            return locationDTO;
        }catch (RuntimeException e){
            throw new RuntimeException("Failed to update this country");
        }
    }
    @Override
    public String deleteById(Long id) {
        try {
            countryRepository.deleteById(id);
            return "Country deleted successfully";
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Failed to delete this member");
        }
    }

}
