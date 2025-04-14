package de.simone.ui.components;

import java.util.List;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.simone.TranslationProvider;
import de.simone.UIUtils;
import de.simone.backend.HanniTask;

public class HanniMessage extends HorizontalLayout {

    private VerticalLayout column;

    public HanniMessage(String stepId) {
        addClassNames(LumoUtility.LineHeight.SMALL);

        String primary = TranslationProvider.getTranslation(stepId + "title");
        String secondary = TranslationProvider.getTranslation(stepId + "message");
        this.column = getPrimaryAndSecondaryComlumn(primary, secondary);

        Avatar avatar = new Avatar(UIUtils.BOT_NAME, UIUtils.BOT_AVATAR);
        add(avatar, column);
    }

    private VerticalLayout getPrimaryAndSecondaryComlumn(String primary, String secondary) {
        Span primaryLabel = new Span(primary);
        primaryLabel.addClassNames(LumoUtility.FontWeight.BOLD);
        Span secondaryLabel = UIUtils.getSpan(secondary, false);
        VerticalLayout column2 = UIUtils.getCompactVerticalLayout(primaryLabel, secondaryLabel);
        primaryLabel.setWidthFull();
        secondaryLabel.setWidthFull();
        return column2;
    }

    public void addTasksItem(List<HanniTask> tasks) {
        tasks.forEach(task -> {
            if (HanniTask.TASK_TYPE.equals(task.type)) {
                HanniTaskItem item = new HanniTaskItem(task.task, task.completed);
                column.add(item);
            }
        });
    }

    public VerticalLayout getColumn() {
        return column;
    }
}
