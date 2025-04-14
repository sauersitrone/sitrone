package de.simone.ui.components;


import java.util.ArrayList;
import java.util.List;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import de.simone.SecurityUtils;
import de.simone.UIUtils;
import de.simone.backend.ChatMessage;
import de.simone.backend.HanniLog;
import de.simone.backend.HanniTask;
import de.simone.backend.ChatsService;
import de.simone.backend.User;
import de.simone.ui.Home;
import jakarta.transaction.Transactional;


@Transactional
public class HanniResourceCenter extends VerticalLayout {

    private VerticalLayout headerLayout = new VerticalLayout();
    private VerticalLayout bodyLayout;
    private User user;
    private Home home;
    private ChatsService notificationsService;

    public HanniResourceCenter() {
        UIUtils.setCompatStyle(this);
        setId(getClass().getSimpleName());
        headerLayout = UIUtils.getTitleH("assitent.separator", false);
        UIUtils.setCompatStyle(headerLayout);
        this.bodyLayout = new VerticalLayout();
        Scroller scroller = new Scroller(bodyLayout);
        UIUtils.setGroupStyle(this);
        add(headerLayout, scroller);
    }

    public void init(Home home) {
        this. user = SecurityUtils.getLoggedUser();
        this.home = home;
        this.notificationsService = home.notificationsService;

        // the first task & button for show onboarding
        addTask("hanni.message01");
        Button button = new Button(getTranslation("task.ONBOARDING.title"), e -> home.startOnboarding());
        HanniMessage message = addMessage("hanni.message02");
        message.getColumn().add(button);

        // check if the first task group is completed
        List<HanniTask> tasks = user.getTasks();
        boolean completed = tasks.stream().allMatch(t -> t.completed);
        if (completed) {
            // Runnable runnable = () -> notificationsService.sendMessage("chatMessage.accountConfirmation",
            //         ChatMessage.ZITRONE_SUPPORT, user.id);
            // message = addMessage("hanni.message03", runnable);
            message.scrollIntoView();
        }
    }

    public HanniMessage addTask(String messageId) {
        // first Task
        if ("hanni.message01".equals(messageId)) {
            List<HanniTask> tasks = new ArrayList<>();
            tasks.add(user.addTask(HanniTask.ONBOARDING));
            tasks.add(user.addTask(HanniTask.CREATE_ACCOUNT));
            tasks.add(user.addTask(HanniTask.CREATE_CAMPAING));
            tasks.add(user.addTask(HanniTask.CREATE_PAYMENT_METHOD));
            tasks.add(user.addTask(HanniTask.CHECK_MAILING));
            tasks.add(user.addTask(HanniTask.CREATE_WIDGET));
            User.getEntityManager().persist(user);
            HanniMessage message = addMessage(messageId);
            message.addTasksItem(tasks);
            return message;
        }

        throw (new IllegalArgumentException("task with messageId '" + messageId + "' not found."));
    }

    public HanniMessage addMessage(String messageId) {
        return addMessage(messageId, null);
    }

    /**
     * add a message to the log.
     * 
     * @param messageId - the message to append
     * @param runnable  - Execute this funcion ONLY if this the message is a new
     *                  message (no already appended)
     * @return the message to show in dashboard
     */
    public HanniMessage addMessage(String messageId, Runnable runnable) {
        boolean addeded = addToLog(messageId, true);
        if (addeded && runnable != null)
            runnable.run();
        HanniMessage message2 = new HanniMessage(messageId + ".");
        bodyLayout.add(message2);
        return message2;
    }

    public boolean addToLog(String messageId, boolean onlyOnce) {
        Long count = HanniLog.count("ownerId = ?1 AND messageId = ?2", user.id, messageId);
        boolean addeded = false;
        if (count == 0 || (count > 0 && !onlyOnce)) {
            HanniLog log = new HanniLog();
            log.messageId = messageId;
            log.setOwner(user);
            log.persist();
            addeded = true;
        }

        return addeded;
    }

}
