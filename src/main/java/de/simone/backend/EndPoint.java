package de.simone.backend;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "EndPoints")
public class EndPoint extends TAEntity {

	@NotBlank
	@NotEmpty
	@Size(max = 255)
	public String target;
	@Size(max = 80)
	public String description;
}
