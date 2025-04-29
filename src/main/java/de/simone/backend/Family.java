package de.simone.backend;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvBindByName;

import de.simone.TranslationProvider;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

@Entity
@Table(name = "Families")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Family extends TAEntity {

    @CsvBindByName
    public boolean isMain;

    @NotNull
    @CsvBindByName
    @OneOfStrings({ Address.WITHOUT, Address.DAME, Address.MISTER })
    public String salutation = Address.WITHOUT;

    @NotNull
    @CsvBindByName
    @OneOfStrings({ Address.DOCTOR, Address.PROFESOR, Address.PROFDOC, Address.NOTITLE })
    public String title = Address.NOTITLE;

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
    @Size(max = SIZE_DESCRIPTIONS)
    public String street;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = 5)
    public String postcode;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String city;

    @NotNull
    @NotBlank
    @CsvBindByName
    @Size(max = SIZE_2)
    public String country = "DE";

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    public String email;

    @Size(max = SIZE_NAMES)
    public String phone;

    @Size(max = SIZE_255)
    public String foto;

    @NotNull
    public LocalDate birdthDate;

    public Family() {
        //
    }

    /**
     * Qute interface
     * 
     * @return - the mime/image
     */
    @JsonIgnore
    public String getEmbeddedFoto() {
        return super.getImage(foto);
    }
}
