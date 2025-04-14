package de.simone.components.dialogs;

import de.simone.components.list.List;
import de.simone.components.list.MessageLinkListItem;
import de.simone.views.HomeView;
import com.vaadin.flow.component.Unit;

import java.time.LocalDateTime;

public class NotificationsDialog extends NativeDialog {

    public NotificationsDialog() {
        setAriaLabel("Notifications");
        setMaxWidth(30, Unit.REM);

        // TODO: Mobile positioning
        // Position
        setRight(0.5f, Unit.REM);
        setTop(3.5f, Unit.REM);

        // Links
        List list = new List(
                new MessageLinkListItem("Sam Rivers", "tagged you in Project Horizon",
                        LocalDateTime.now().minusHours(2), HomeView.class),
                new MessageLinkListItem("Marcus Peters", "commented on Eclipse Estimates Q1",
                        LocalDateTime.now().minusHours(4), HomeView.class)
        );
        add(list);
    }

}
