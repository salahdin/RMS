package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Country {
	
	@Id
	private String code;
	
	private String name;
	
	private Integer population;

	@OneToMany(mappedBy = "country")
	private List<State> states = new ArrayList<>() ;

	@Embedded
	private AuditData auditData;
	
}
