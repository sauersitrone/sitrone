package de.simone.backend;

import java.util.*;

import org.hibernate.annotations.*;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import net.andreinc.jbvext.annotations.misc.*;

@Entity
@Table(name = "Adults")
public class Adult extends TAEntity {

    public static final String DAME = "DAME";
    public static final String MISTER = "MISTER";
    public static final String WITHOUT = "WITHOUT";

    public static final String CARER = "CARER";
    public static final String FAMILY = "FAMILY";
    public static final String CARER_AND_FAMILY = "CARER_AND_FAMILY";

    @NotNull
    public Long carerId;

    @NotNull
    public Long tamagotchiId;

    @NotNull
    @OneOfStrings({ CARER, FAMILY, CARER_AND_FAMILY })
    public String relationship = CARER_AND_FAMILY;

    @NotNull
    @OneOfStrings({ WITHOUT, DAME, MISTER })
    public String salutation = WITHOUT;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String firstName;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String lastName;

    @Size(max = SIZE_255)
    public String avatar;

    @Email
    @NotBlank
    @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    public String email;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String phone;

    @NotEmpty
    @ElementCollection
    public Set<String> interests = new HashSet<>();

    @NotNull
    @OneOfStrings({ EN, DE })
    public String genre = DE;

    @NotNull
    @OneOfStrings({ EN, DE })
    public String preferredLanguage = DE;

    @NotNull
    @Min(value = 1900)
    public Integer birdthYear;

    public String getFullName() {
        return Address.getFullName(firstName, lastName);
    }
}
