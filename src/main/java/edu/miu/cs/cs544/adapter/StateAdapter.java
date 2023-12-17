package edu.miu.cs.cs544.adapter;

import edu.miu.cs.cs544.domain.State;
import edu.miu.cs.cs544.dto.StateDTO;
import org.springframework.stereotype.Component;

@Component
public class StateAdapter {

    public StateDTO entityToDTO(State state) {
        return new StateDTO( state.getCode(), state.getName(), state.getCountry() );
    }
}
