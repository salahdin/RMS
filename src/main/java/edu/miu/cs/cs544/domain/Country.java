package edu.miu.cs.cs544.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@Column(unique=true)
	private String code;

	@Column(unique=true)
	private String name;
	
	private Integer population;

	@JsonIgnore
	@OneToMany(mappedBy = "country")
	private List<State> states = new ArrayList<>() ;

	@Embedded
	private AuditData auditData = new AuditData();

	public Country(Long id, String code, String name, Integer population, List<State> states, AuditData auditData) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.population = population;
		this.states = states;
		this.auditData = auditData;
	}

	public Country(Long id, String code, String name, Integer population, List<State> states) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.population = population;
		this.states = states;
	}

	public Country(Long id, String code, String name, Integer population) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.population = population;
	}
}
