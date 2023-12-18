package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.CountryDTO;
import edu.miu.cs.cs544.dto.StateDTO;

import java.util.List;

public interface StateService {

    public StateDTO addCountry(StateDTO locationDTO);
    public List<StateDTO> findAllStates();
    public StateDTO findById(Integer id);
    public StateDTO updateState(StateDTO locationDTO);
    public String deleteById(Integer id);
}
