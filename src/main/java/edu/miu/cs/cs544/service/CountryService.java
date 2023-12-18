package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.CountryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {
    public CountryDTO addCountry(CountryDTO locationDTO);
    public List<CountryDTO> findAllCountries();
    public CountryDTO findById(Long id);
    public CountryDTO updateCountry(CountryDTO locationDTO);
    public String deleteById(Long id);
}
