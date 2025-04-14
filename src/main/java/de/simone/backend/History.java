package de.simone.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

@Entity
@Table(name = "Histories")
public class History extends TAEntity {

  public static final String SCARED = "SCARED";
  public static final String HAPPY = "HAPPY";
  public static final String SAD = "SAD";
  public static final String EXCITED = "EXCITED";
  public static final String WORRIED = "WORRIED";
   
  public static final String MANUAL = "MANUAL";
  public static final String GENERATED = "GENERATED";

  @NotNull
  @OneOfStrings({SCARED, HAPPY, SAD, EXCITED, WORRIED})
  public String mood = HAPPY;

  @NotNull
  @OneOfStrings({MANUAL, GENERATED})
  public String type = GENERATED;

  @Size(max = SIZE_255)
  public String note;

  public Long adultId;
  public Integer height;
  public Integer weight;
}
