package edu.miu.cs.cs544.domain;

import edu.miu.cs.cs544.domain.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name; 
	
	private String description;
	
	private String excerpt;

	@Enumerated(EnumType.STRING)
	private ProductType type;

	@Embedded
	private AuditData auditData = new AuditData();

	public Product(String name, String description, String excerpt, ProductType type, AuditData auditData) {
		this.name = name;
		this.description = description;
		this.excerpt = excerpt;
		this.type = type;
		this.auditData = auditData;
	}
}
