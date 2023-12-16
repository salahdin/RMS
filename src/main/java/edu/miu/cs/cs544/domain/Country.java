package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Country {

	@Id
	@GeneratedValue
	private Long id;

	private String code;
	
	private String name;
	
	private Integer population;

	@OneToMany(mappedBy = "country")
	private List<State> states = new ArrayList<>() ;

	@Embedded
	private AuditData auditData;

	public Country(String code, String name, Integer population, List<State> states, AuditData auditData) {
		this.code = code;
		this.name = name;
		this.population = population;
		this.states = states;
		this.auditData = auditData;
	}
}
