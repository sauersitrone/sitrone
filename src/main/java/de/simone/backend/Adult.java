package de.simone.backend;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import net.andreinc.jbvext.annotations.misc.*;

@Entity
@Table(name = "Adults")
public class Adult extends TAEntity implements UserDomain {

    public static final String WOMAN = "WOMAN";
    public static final String MAN = "MAN";

    public static final String NOESPEC = "NOESPEC";
    public static final String SINGLE = "SINGLE";
    public static final String MARRIED = "MARRIED";
    public static final String DIVORCED = "DIVORCED";
    public static final String WIDOW = "WIDOW";

    public static final String CARER = "CARER";
    public static final String FAMILY = "FAMILY";
    public static final String CARER_AND_FAMILY = "CARER_AND_FAMILY";

    @NotNull
    @OneOfStrings({ CARER, FAMILY, CARER_AND_FAMILY })
    public String relationship = CARER_AND_FAMILY;

    @NotNull
    public Long tamagotchiId;

    @NotNull
    @OneOfStrings({ WOMAN, MAN })
    public String gender = MAN;

    @NotNull
    @OneOfStrings({ NOESPEC, SINGLE, MARRIED, DIVORCED, WIDOW })
    public String maritalStatus = NOESPEC;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String firstName;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String lastName;

    @Size(max = SIZE_255)
    public String picture;

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
    @ElementCollection(fetch = FetchType.EAGER)
    public Set<String> personality = new HashSet<>();

    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    public Set<String> interests = new HashSet<>();

    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    public Set<String> ocupation = new HashSet<>();

    @NotNull
    @OneOfStrings({ EN, DE })
    public String preferredLanguage = DE;

    @NotNull
    public LocalDate birdthDate;

    public String getFullName() {
        return Address.getFullName(firstName, lastName);
    }

    public Integer getAge() {
        LocalDate now = LocalDate.now();
        long years = ChronoUnit.YEARS.between(birdthDate, now);
        return Integer.valueOf("" + years);
    }

        /**
     * Qute interface
     * 
     * @return - the mime/image
     */
    @JsonIgnore
    public String getEmbeddedPicture() {
        return super.getImage(picture);
    }

    
    public String getPersonalityValues() {
        return geListOfValues("person.personality", personality);
    }

    public String getInterestsValues() {
        return geListOfValues("person.interests", interests);
    }

    public String getOcupationValues() {
        return geListOfValues("person.ocupation", ocupation);
    }

}
