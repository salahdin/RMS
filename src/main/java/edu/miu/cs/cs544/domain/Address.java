package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
public class Address {

	@Id
	@GeneratedValue
	private Integer id;

	private String line1;
	
	private String line2;
	
	private String city;
	
	private String postalCode;


	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	@Embedded
	private AuditData auditData;

	@ManyToOne
	private State state;
	
}
