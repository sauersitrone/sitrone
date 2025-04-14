package de.simone.backend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

@Entity
@Table(name = "Events")
public class Event extends TAEntity {

  public static final String PERSONAL = "PERSONAL";
  public static final String MEDICATION = "MEDICATION";
  public static final String EXERCISE = "EXERCISE";
  public static final String SHOPPING = "SHOPPING";
  public static final String CALENDAR = "CALENDAR";
  public static final String DIET = "DIET";
  public static final String OTHER = "OTHER";

  public static final String ATTENDED = "ATTENDED";
  public static final String MISSED = "MISSED";
  public static final String WAITING = "WAITING";

  @NotNull
  @OneOfStrings({PERSONAL, MEDICATION, EXERCISE, SHOPPING, CALENDAR, DIET, OTHER})
  public String type = PERSONAL;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_NAMES)
  public String title;

  @NotNull
  @OneOfStrings({ATTENDED, MISSED, WAITING})
  public String status = WAITING;

  // @NotBlank
  // @Size(min = 7, max = 7)
  //   public String color = UIUtils.BASE_COLOR;

  @Column(columnDefinition = "TEXT")
  public String content;

  @Column(columnDefinition = "TEXT")
  public String note;

  @NotNull
  @Min(value = 3)
  public Integer reminderCount=3;

  // public LocalDate scheduledAt;
  public boolean canBeMissed;
  public String reminderInterval = "PT5M";

  // public LocalDate triggeredAt;

}
