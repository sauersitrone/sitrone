package de.simone.backend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Queries")
public class Query extends TAEntity {

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    public String className;
    @NotBlank
    @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    public String description;
    @NotBlank
    @NotEmpty
    @Size(max = SIZE_TEXT)
    @Column(columnDefinition = "TEXT")
    public String partialWhere;
}
