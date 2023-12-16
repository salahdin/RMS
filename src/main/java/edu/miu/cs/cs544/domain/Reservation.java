package edu.miu.cs.cs544.domain;

import java.util.List;

import edu.miu.cs.cs544.domain.enums.ReservationState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Reservation {

	@Id
	@Generated
	private Integer id;

	@ManyToOne
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation", fetch = FetchType.EAGER)
	private List<Item> items;

	@Embedded
	private AuditData auditData;

	public Reservation(Customer customer, AuditData auditData) {
		this.customer = customer;
		this.auditData = auditData;
	}

	@Enumerated
	public ReservationState reservationState;
}
