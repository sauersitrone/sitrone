package de.simone.backend;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Locale;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

@Entity
public class Prescription extends TAEntity {

  public static final CronDefinition CRON_DEFINITION =
      CronDefinitionBuilder.instanceDefinitionFor(CronType.CRON4J);

  public static final String ONCE = "ONCE";
  public static final String DAILY = "DAILY";
  public static final String WEEK_DAY = "WEEK_DAY";
  public static final String WEEKLY = "WEEKLY";
  // public static final String MONTHLY_DAY = "MONTHLY_DAY";
  public static final String MONTHLY_DATE = "MONTHLY_DATE";
  // public static final String ANNUALLY = "ANNUALLY";

  public static final String PT1M = "PT1M";
  public static final String PT5M = "PT5M";
  public static final String PT15M = "PT15M";
  public static final String PT30M = "PT30M";
  public static final String PT1H = "PT1H";
  public static final String PT4H = "PT4H";
  public static final String PT24H = "PT24H";

  @OneToOne public Drug drug;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_DESCRIPTIONS)
  public String description;

  // @NotBlank
  // @NotEmpty
  // @Size(max = SIZE_255)
  // public String textReminder;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_DESCRIPTIONS)
  public String cronString;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_255)
  public String indications;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_255)
  public String contraindications;

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_DESCRIPTIONS)
  public String dosage;

  @NotNull public LocalDateTime calendarWhen = LocalDateTime.now();

  @NotNull
  @OneOfStrings({ONCE, DAILY, WEEK_DAY, WEEKLY, MONTHLY_DATE})
  public String calendarRepeat = ONCE;

  @NotNull
  @OneOfStrings({PT1M, PT5M, PT15M, PT30M, PT1H, PT4H, PT24H})
  public String calendarRemind = PT1M;

  public Long adultId;
  public Integer quantity;

  public String getCronDescription() {
    CronParser parser = new CronParser(CRON_DEFINITION);
    Cron cron = parser.parse(cronString);
    CronDescriptor descriptor = CronDescriptor.instance(Locale.GERMANY);
    String cDescription = descriptor.describe(cron);
    return cDescription;
  }
}
