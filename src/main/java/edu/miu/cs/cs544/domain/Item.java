package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Item {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer occupants;
	
	private LocalDate checkinDate;

	private LocalDate checkoutDate;

	@ManyToOne
	private Reservation reservation;

	@OneToOne
	private Product product;

	@Embedded
	private AuditData auditData;
	
}
