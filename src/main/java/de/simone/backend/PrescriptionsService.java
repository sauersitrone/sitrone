package de.simone.backend;

import com.cronutils.builder.CronBuilder;
import com.cronutils.model.Cron;
import com.cronutils.model.field.expression.FieldExpressionFactory;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import de.simone.TranslationProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@ApplicationScoped
public class PrescriptionsService extends TAService<Prescription> {

  public PrescriptionsService() {
    super(Prescription.class);
  }

  @Override
  public Prescription get(Long id) throws WebApplicationException {
    return getImpl(id);
  }

  @Override
  @Transactional
  public Response delete(Long id) {
    return deleteImpl(id);
  }

  @Override
  @Transactional
  public Response save(Prescription entity) {
    CronBuilder cronBuilder = CronBuilder.cron(Prescription.CRON_DEFINITION);

    cronBuilder.withMinute(FieldExpressionFactory.on(entity.calendarWhen.getMinute()));
    cronBuilder.withHour(FieldExpressionFactory.on(entity.calendarWhen.getHour()));
    cronBuilder.withDoM(FieldExpressionFactory.always());
    cronBuilder.withDoW(FieldExpressionFactory.always());
    cronBuilder.withMonth(FieldExpressionFactory.always());

    if (Prescription.ONCE.equals(entity.calendarRepeat)) {
      cronBuilder.withDoM(FieldExpressionFactory.on(entity.calendarWhen.getDayOfMonth()));
      cronBuilder.withMonth(FieldExpressionFactory.on(entity.calendarWhen.getMonth().getValue()));
    }

    if (Prescription.DAILY.equals(entity.calendarRepeat))
      cronBuilder.withDoW(FieldExpressionFactory.always());

    if (Prescription.WEEK_DAY.equals(entity.calendarRepeat))
      cronBuilder.withDoW(FieldExpressionFactory.between(1, 5));

    if (Prescription.WEEKLY.equals(entity.calendarRepeat))
      cronBuilder.withDoW(FieldExpressionFactory.on(entity.calendarWhen.getDayOfWeek().getValue()));

    if (Prescription.MONTHLY_DATE.equals(entity.calendarRepeat))
      cronBuilder.withDoM(FieldExpressionFactory.on(entity.calendarWhen.getDayOfMonth()));

    Cron cron = cronBuilder.instance();
    entity.cronString = cron.asString();
    return saveImpl(entity);
  }

  @Override
  public Response duplicate(Long id) {
    return duplicateImpl(id);
  }

  @Transactional
  public PrescriptionCheck checkPrescription(Prescription prescription) {

    CronParser parser = new CronParser(Prescription.CRON_DEFINITION);
    // Get date for last execution
    ZonedDateTime now = ZonedDateTime.now();
    ExecutionTime executionTime = ExecutionTime.forCron(parser.parse(prescription.cronString));

    ZonedDateTime lastExecution = executionTime.lastExecution(now).get();
    ZonedDateTime nextExecution = executionTime.nextExecution(now).get();

    Duration timeFromLastExecution = executionTime.timeFromLastExecution(now).get();
    Duration timeToNextExecution = executionTime.timeToNextExecution(now).get();

    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);

    String msg =
        TranslationProvider.getTranslation(
            "Response.prescriptionCheck",
            prescription.description,
            formatter.format(lastExecution),
            formatter.format(nextExecution));
    boolean isMatch = executionTime.isMatch(now);
    PrescriptionCheck check = new PrescriptionCheck(msg, isMatch);
    return check;
  }

  public static record PrescriptionCheck(String message, boolean isMatch) {}
}
