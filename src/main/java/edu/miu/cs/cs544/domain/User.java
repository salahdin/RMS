package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@Generated
	private Integer id;
	
	private String userName;
	
	private String userPass;
	
	private Boolean active;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Embedded
	private AuditData auditData;

}
