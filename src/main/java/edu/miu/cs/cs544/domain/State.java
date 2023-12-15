package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
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

}
