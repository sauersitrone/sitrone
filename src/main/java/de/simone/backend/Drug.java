package de.simone.backend;

import de.simone.UIUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

@Entity
@Table(name = "Drugs")
public class Drug extends TAEntity { 

  public static final String CAPSULE = "CAPSULE";
  public static final String OVAL = "OVAL";
  public static final String ROUND = "ROUND";
  public static final String PENTAGON = "PENTAGON";
  public static final String BULLET = "BULLET";

  @NotNull
  @OneOfStrings({CAPSULE, OVAL, ROUND, PENTAGON, BULLET})
  public String shape = CAPSULE;

  @Size(max = SIZE_DESCRIPTIONS)
  public String imprint;

  @NotBlank
  @NotEmpty
  @Size(min = 7, max = 7)
  public String primaryColor = UIUtils.BASE_COLOR;

  @Size(min = 7, max = 7)
  public String secundaryColor = UIUtils.BASE_COLOR;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_NAMES)
  public String supplier;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_NAMES)
  public String ndc9;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_NAMES)
  public String name;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_255)
  public String imageMedicament;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_255)
  public String imageBox;

  @Min(value = 1)
  public Integer strength;

  // mark when the app muss eine Erinerung auslösen
  @Min(value = 1)
  public Integer refillFrom;
}
