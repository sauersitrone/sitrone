package de.simone.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "HanniTasks")
public class HanniTask extends TAEntity implements UserDomain {

    // Hybrid Artificial Neural Network Integration

    // TASK
    public static final String ONBOARDING = "ONBOARDING";
    public static final String CREATE_ACCOUNT = "CREATE_ACCOUNT";
    public static final String CREATE_CAMPAING = "CREATE_CAMPAING";
    public static final String CREATE_PAYMENT_METHOD = "CREATE_PAYMENT_METHOD";
    public static final String CHECK_MAILING = "CHECK_MAILING";
    public static final String CREATE_WIDGET = "CREATE_WIDGET";

    public static final String NO_DONATIONS = "NO_DONATIONS";
    public static final String NO_PETITIONS = "NO_PETITIONS";
    public static final String NO_SUPPORTERS = "NO_SUPPORTERS";

    // TIPS
    public static final String TIP_USERS = "TIP_USERS";
    public static final String TIP_PAYMENT_METHOD = "TIP_PAYMENT_METHOD";
    public static final String TIP_MAILING = "TIP_MAILING";
    public static final String TIP_NOTIFICATION_CHANNEL = "TIP_NOTIFICATION_CHANNEL";
    public static final String TIP_ROLLE = "TIP_ROLLE";

    public static final String TASK_TYPE = "TASK";
    public static final String TIP_TYPE = "TIP";

    public String type = TASK_TYPE;
    public String task;
    public boolean completed;

    public HanniTask() {
        // empty
    }

    public HanniTask(String task) {
        this.task = task;
    }
}
