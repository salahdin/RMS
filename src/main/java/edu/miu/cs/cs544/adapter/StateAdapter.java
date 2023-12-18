package edu.miu.cs.cs544.adapter;

import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.domain.State;
import edu.miu.cs.cs544.dto.CountryDTO;
import edu.miu.cs.cs544.dto.StateDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateAdapter {
    public StateDTO entityToDto(State state){
        return new StateDTO(state.getId(), state.getCode(), state.getName(), state.getCountry());
    }
    public List<StateDTO> entityToDtoAll(List<State> states){
        return states.stream().map(state -> entityToDto(state)).collect(Collectors.toList());
    }
    public State dtoToEntity(StateDTO stateDTO){
        return new State(stateDTO.getId(), stateDTO.getCode(), stateDTO.getName(), stateDTO.getCountry());
    }
    public List<State> dtoToEntityAll(List<StateDTO> stateDTOS){
        return stateDTOS.stream().map(stateDTO -> dtoToEntity(stateDTO)).collect(Collectors.toList());
    }
}
