package de.simone.ui.components;

import java.util.*;

import org.apache.commons.lang3.*;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.theme.lumo.*;

import de.simone.*;
import de.simone.backend.*;

public class ViewEmptyPage extends VerticalLayout {

    private VerticalLayout contentLayout;
    private boolean hastThePageContent = true;

    public ViewEmptyPage(Class<? extends Component> clazz) {
        addClassName(LumoUtility.Background.CONTRAST_90);

        String clsName = UIUtils.getSimpleClassName(clazz);
        Image image = new Image(UIUtils.IMG_PATH + clsName + ".png", clsName);
        Span title = new Span(getTranslation(clsName));
        title.addClassNames(LumoUtility.FontSize.XXLARGE);

        String txt = getTranslation(clsName + ".empty.message", clsName);
        hastThePageContent = StringUtils.isNotBlank(txt);
        Span message = UIUtils.getSpan(txt, false);

        contentLayout = new VerticalLayout(image, title, message);
        contentLayout.setWidth("70%");
        contentLayout.setHeight("70%");

        VerticalLayout verticalLayout = new VerticalLayout(contentLayout);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setSizeFull();
        UIUtils.setGroupStyle(verticalLayout);

        add(verticalLayout);
    }

    /**
     * retunr true if existe a correspondin "ClazzName.empty.message" entry int the
     * property file. if not, is assumed, that is not necesary show a empty page.
     * 
     * @return true or false
     */
    public boolean hastThePageContent() {
        return hastThePageContent;
    }

    /**
     * return true if the current user has pendig task or blocking task. if are
     * pendig task, this method add the corresponding text component to guide the
     * user. for blocking task the content reamin the same but the user muss wait to
     * somenthing
     * 
     * @param taskToCheck - the tast to check
     * @return true if are pendig task
     */
    public boolean checkTasks(String... taskToCheck) {
        // no task to check (there is no need to emptypage)
        if (taskToCheck == null)
            return false;

        User user = SecurityUtils.getLoggedUser();
        Long secondaryKey = user.id;
        List<ListItem> messages = new ArrayList<>();
        String query = "secondaryKey = ?1";
        String query2 = "secondaryKey = ?1 AND contributionType = ?2";
        boolean onlyBlockAccess = false;

        for (String task : taskToCheck) {
            String messageId = "task." + task + ".checkTask";
            String text = getTranslation(messageId);

            // account was at least visited
            if (HanniTask.CREATE_ACCOUNT.equals(task)
                    && !user.isTaskCompleted(HanniTask.CREATE_ACCOUNT)) {
                messages.add(new ListItem(text));
            }

            // there ist at least 1 campaig
            if (HanniTask.CREATE_CAMPAING.equals(task)) {
                // Long count = Campaign.count(query, secondaryKey);
                // if (count == 0) {
                // messages.add(new ListItem(text));
                // }
            }

            // there ist at least 1 payment method
            if (HanniTask.CREATE_PAYMENT_METHOD.equals(task)) {
                // Long count = PaymentMethod.count(query, secondaryKey);
                // if (count == 0) {
                // messages.add(new ListItem(text));
                // }
            }

            // there ist at least 1 conversion widget
            if (HanniTask.CREATE_WIDGET.equals(task)) {
                // Long count = Widget.count(query, secondaryKey);
                // if (count == 0) {
                // messages.add(new ListItem(text));
                // }
            }

            // mailing was at least visited
            if (HanniTask.CHECK_MAILING.equals(task)
                    && !user.isTaskCompleted(HanniTask.CHECK_MAILING)) {
                messages.add(new ListItem(text));
            }

            // BLOCKING TASK
            if (HanniTask.NO_DONATIONS.equals(task)) {
                // Long count = Contribution.count(query2, secondaryKey, Contribution.DONATION);
                // onlyBlockAccess = (onlyBlockAccess) ? onlyBlockAccess : count == 0;
            }

            if (HanniTask.NO_PETITIONS.equals(task)) {
                // Long count = Contribution.count(query2, secondaryKey, Contribution.PETITION);
                // onlyBlockAccess = (onlyBlockAccess) ? onlyBlockAccess : count == 0;
            }

            if (HanniTask.NO_SUPPORTERS.equals(task)) {
                // Long count = Supporter.count(query, secondaryKey);
                // onlyBlockAccess = (onlyBlockAccess) ? onlyBlockAccess : count == 0;
            }
        }

        // add the messages
        boolean pendigTask = !messages.isEmpty();
        if (pendigTask) {
            Span title = new Span(getTranslation("ViewEmptyPage.checkTitle"));
            title.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.TextColor.ERROR);
            contentLayout.add(title);
            OrderedList orderedList = new OrderedList(messages.toArray(new ListItem[0]));
            contentLayout.add(orderedList);
        }
        return pendigTask || onlyBlockAccess;
    }
}
