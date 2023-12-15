package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Customer {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String firstName;
	
	private String lastName;

	private String email;


	@OneToMany(mappedBy = "customer")
	List<Reservation> reservations = new ArrayList<>();

	@Embedded
	private AuditData auditData;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="billing_address_id")
	private Address billingAddress;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="physical_address_id")
	private Address physicalAddress;

}
