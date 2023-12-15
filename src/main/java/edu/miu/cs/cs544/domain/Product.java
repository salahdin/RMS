package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Product {

	@Id
	@Generated
	private Integer id;
	
	private String name; 
	
	private String description;
	
	private String excerpt;

	@Enumerated(EnumType.STRING)
	private ProductType type;

	@Embedded
	private AuditData auditData;
	
}
