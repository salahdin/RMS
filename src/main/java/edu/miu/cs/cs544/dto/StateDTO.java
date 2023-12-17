package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.AuditData;
import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.domain.State;
import edu.miu.cs.cs544.domain.enums.AddressType;
import jakarta.persistence.Embedded;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StateDTO {

    private String code;

    private String name;

    // may need to change to Country DTO
    private Country country;
}

