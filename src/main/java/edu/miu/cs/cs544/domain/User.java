package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String userName;
	
	private String userPass;
	
	private Boolean active;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Embedded
	private AuditData auditData;

	public User(String userName, String userPass, Boolean active, UserType userType, AuditData auditData) {
		this.userName = userName;
		this.userPass = userPass;
		this.active = active;
		this.userType = userType;
		this.auditData = auditData;
	}
}
