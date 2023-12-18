package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Country;
import lombok.Data;

@Data
public class StateDTO {

    private String code;

    private String name;

    private CountryDTO country;

    public StateDTO(String code, String name, CountryDTO country) {
        this.code = code;
        this.name = name;
        this.country = country;
    }
}
