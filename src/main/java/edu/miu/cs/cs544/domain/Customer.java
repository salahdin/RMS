package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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

	public Customer(String firstName, String lastName, String email, AuditData auditData, User user, Address billingAddress, Address physicalAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.auditData = auditData;
		this.user = user;
		this.billingAddress = billingAddress;
		this.physicalAddress = physicalAddress;
	}
}
