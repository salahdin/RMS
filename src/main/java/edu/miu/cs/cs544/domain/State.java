package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class State {

	@Id
	@GeneratedValue
	private Integer id;

	private String code;
	
	private String name;

	@Embedded
	private AuditData auditData;

	@ManyToOne
	private Country country;

	public State(String code, String name, AuditData auditData, Country country) {
		this.code = code;
		this.name = name;
		this.auditData = auditData;
		this.country = country;
	}
}
