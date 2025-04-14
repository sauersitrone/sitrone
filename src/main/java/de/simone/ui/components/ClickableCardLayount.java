package de.simone.ui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.simone.UIUtils;
import de.simone.backend.TAEntity;
import de.simone.ui.TAView;

public class ClickableCardLayount<E extends TAEntity> extends Div
        implements ComponentEventListener<ClickEvent<HorizontalLayout>> {

    protected List<ClickableCardMedium> cards;
    protected List<Component> actions;
    private ContextMenu contextMenu;
    private List<E> items;
    private Long selected;
    private SerializablePredicate<E> filter;
    private TAView<E> taView;

    public ClickableCardLayount() {
        cards = new ArrayList<>();
        items = new ArrayList<>();
        setHeightFull();
        setWidth("98%");
        addClassNames(LumoUtility.Display.GRID, LumoUtility.Grid.Column.COLUMNS_3,
                LumoUtility.AlignContent.START, LumoUtility.Gap.MEDIUM, LumoUtility.Margin.MEDIUM);
    }

    public void setItems(List<E> items) {
        this.items = items;
        addCards(items);
    }

    private void addCards(List<E> items) {
        cards.clear();
        removeAll();
        for (E item : items) {
            ClickableCardMedium card = new ClickableCardMedium();
            card.setId("" + item.id);
            card.setEntity(item);
            card.addClickListener(this);
            // card.getElement().addEventListener("dblclick", e -> taView.doubleCLick());
            cards.add(card);
            add(card);
        }
    }

    public void setTAView(TAView<E> taView) {
        this.taView = taView;
    }

    public void setActions(List<Component> actions) {
        this.actions = actions;
        Optional<Component> optional2 = actions.stream()
                .filter(a -> a.getId().orElse("null").equals(UIUtils.CARD_CONTEXT_MENU)).findFirst();
        if (optional2.isPresent()) {
            contextMenu = (ContextMenu) optional2.get();
        }
    }

    public Optional<E> getSelected() {
        if (selected == null)
            return Optional.empty();

        return items.stream().filter(e -> e.id.equals(selected)).findFirst();
    }

    public void addFilter(SerializablePredicate<E> filter) {
        this.filter = filter;
    }

    public void refreshAll() {
        if (filter == null)
            return;
        List<E> list = items.stream().filter(filter::test).collect(Collectors.toList());
        addCards(list);
    }

    @Override
    public void onComponentEvent(ClickEvent<HorizontalLayout> event) {
        ClickableCardMedium card = (ClickableCardMedium) event.getSource();
        cards.forEach(c -> c.setSelected(false));
        boolean isPresent = !event.isCtrlKey();
        card.setSelected(isPresent);

        selected = null;
        Optional<String> optional = card.getId();
        if (isPresent && optional.isPresent())
            selected = Long.parseLong(optional.get());

        contextMenu.setTarget(card);

        // enable/disable toolbar
        actions.forEach(mi -> {
            if (mi.getElement().getProperty(UIUtils.IS_EDIT_ACTION, false))
                ((Button) mi).setEnabled(isPresent);
        });

        // enable/disable context menu
        contextMenu.getChildren().forEach(mi -> {
            if (mi.getElement().getProperty(UIUtils.IS_EDIT_ACTION, false))
                ((MenuItem) mi).setEnabled(isPresent);
        });
    }
}
