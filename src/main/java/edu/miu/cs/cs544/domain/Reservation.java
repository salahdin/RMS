package edu.miu.cs.cs544.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Entity
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
	
}