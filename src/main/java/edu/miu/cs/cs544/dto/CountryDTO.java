package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private Long id;

    private String code;

    private String name;

    private Integer population;

    private List<State> states = new ArrayList<>() ;

    public CountryDTO(Long id) {
        this.id = id;
    }

    public CountryDTO(Long id, String name, Integer population, List<State> states) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.states = states;
    }
}
