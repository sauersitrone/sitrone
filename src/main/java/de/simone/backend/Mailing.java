package de.simone.backend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

@Entity
@Table(name = "Mailings")
public class Mailing extends TAEntity {

	public static final String TEXT = "TEXT";
	public static final String HTML = "HTML";

	public static final String INTERNAL = "INTERNAL";
	public static final String DONATION = "DONATION";
	public static final String PETITION = "PETITION";

	@NotBlank
	@NotEmpty
	@Size(max = SIZE_NAMES)
	public String mailingName;

	@NotBlank
	@NotEmpty
	@Size(max = SIZE_DESCRIPTIONS)
	public String description;

	@NotBlank
	@NotEmpty
	@Size(max = SIZE_255)
	public String subject;

    @NotNull
	@OneOfStrings({ TEXT, HTML })
	public String type = HTML;

	@NotBlank
	@NotEmpty
	@Column(columnDefinition = "TEXT")
	public String message;
    
    @NotNull
	@OneOfStrings({ INTERNAL, DONATION, PETITION })
	public String audience = DONATION;

    public boolean isTemplate; 
}
