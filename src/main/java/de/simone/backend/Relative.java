package de.simone.backend;

import java.time.*;

import com.fasterxml.jackson.annotation.*;
import com.opencsv.bean.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Relatives")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Relative extends TAEntity {

    @NotNull
    public Long adultId;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String firstName;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String lastName;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String relation = "Father";

    @Size(max = SIZE_255)
    public String picture;

    @NotNull
    public LocalDate birdthDate;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Column(columnDefinition = "TEXT")
    public String details;

}
