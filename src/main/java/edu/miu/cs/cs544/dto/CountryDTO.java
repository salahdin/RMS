package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CountryDTO {
    private Long id;

    private String code;

    private String name;

    private Integer population;

//    private List<State> states = new ArrayList<>();

    public CountryDTO(Long id) {
        this.id = id;
    }

//    public CountryDTO(Long id, String name, Integer population, List<State> states) {
//        this.id = id;
//        this.name = name;
//        this.population = population;
//        this.states = states;
//    }

    public CountryDTO(String code, String name, Integer population) {
        this.code = code;
        this.name = name;
        this.population = population;
    }

    public CountryDTO(Long id, String code, String name, Integer population) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.population = population;
    }
}
