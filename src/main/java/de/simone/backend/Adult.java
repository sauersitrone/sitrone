package de.simone.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

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
  @OneOfStrings({CARER, FAMILY, CARER_AND_FAMILY})
  public String relationship = CARER_AND_FAMILY;
  
    @NotNull
    @OneOfStrings({WITHOUT, DAME, MISTER})
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

  @NotNull
  @OneOfStrings({EN, DE})
  public String preferredLanguage = DE;

  @NotNull
  @Min(value = 1900)
  public Integer birdthYear;

  public String getFullName() {
    return Address.getFullName(firstName, lastName);
  }
}
