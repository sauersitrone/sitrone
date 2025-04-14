package de.simone.backend;

import de.simone.TranslationProvider;
import de.simone.backend.PrescriptionsService.PrescriptionCheck;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ScheduledTasksExecutor {

  @Inject PrescriptionsService prescriptionsService;

  @Transactional
  @Scheduled(every = "55s")
  public void checkTamagotchies() {
    Long t1 = System.currentTimeMillis();
    List<Tamagotchi> tamagotchies = Tamagotchi.listAll();
    for (Tamagotchi tamagotchi : tamagotchies) {
      if (!tamagotchi.isActive()) tamagotchi.timePassed();
    }
    log("Tamagotchies checked", tamagotchies.size(), t1);
  }

  @Transactional
  @Scheduled(every = "55s")
  public void checkPrescriptions() {
    Long t1 = System.currentTimeMillis();
    List<Prescription> prescriptions = Prescription.listAll();
    for (Prescription prescription : prescriptions) {
      PrescriptionCheck check = prescriptionsService.checkPrescription(prescription);
      if (check.isMatch()) {
        Event event = new Event();
        event.canBeMissed = false;
        event.canBeMissed = false;
        event.content =
            TranslationProvider.getTranslation(
                "Event.takeMedicine.content", prescription.drug.name);
        event.title = TranslationProvider.getTranslation("Event.takeMedicine.title");
        event.type = Event.MEDICATION;
        event.persist();
      }
    }

    log("Prescriptions checked", prescriptions.size(), t1);
  }

  private void log(String message, long elements, long t1) {
    long time = System.currentTimeMillis() - t1;
    String message2 =
        message
            + " performed at "
            + new Date()
            + ". Processed elements: "
            + elements
            + ". Execution time: "
            + (time / 1000)
            + "s.";

    // if (!GlobalProperty.isLocalhost())
    // Log.info(message2);
  }
}
