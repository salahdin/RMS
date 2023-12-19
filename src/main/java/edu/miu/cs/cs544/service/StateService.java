package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.State;
import edu.miu.cs.cs544.dto.CountryDTO;
import edu.miu.cs.cs544.dto.StateDTO;

import java.util.List;

public interface StateService {

    public StateDTO addState(StateDTO stateDTO);
    public State addState(State state);
    public List<StateDTO> findAllStates();
    public StateDTO findById(Integer id);
    public StateDTO updateState(StateDTO stateDTO);
    public String deleteById(Integer id);
}
