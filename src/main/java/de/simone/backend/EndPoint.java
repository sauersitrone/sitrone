package de.simone.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "EndPoints", indexes = {
        @Index(name = "EndPoints_ownerId", columnList = "ownerId") })
public class EndPoint extends TAEntity {

	@NotBlank
	@NotEmpty
	@Size(max = 255)
	public String target;
	@Size(max = 80)
	public String description;
}
