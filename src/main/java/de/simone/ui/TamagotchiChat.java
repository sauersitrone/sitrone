package de.simone.ui;

import org.vaadin.lineawesome.*;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.checkbox.*;
import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.messages.*;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.dom.*;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import de.simone.*;
import de.simone.backend.*;
import de.simone.components.Layout;
import de.simone.ui.components.*;
import de.simone.utilities.*;
import jakarta.annotation.security.*;
import jakarta.inject.*;
import jakarta.transaction.*;

@RolesAllowed({ "Sitrone.master", "Tamagotchies" })
@Route(value = "TamagotchiChat", layout = MainLayout.class)
public class TamagotchiChat extends HorizontalLayout
        implements BeforeEnterObserver, PropertyChangeListener {

    private H1 emotionEmoji;
    private Paragraph paragraph;
    private Adult adult;
    private Tamagotchi tamagotchi;
    private TTimer timer;
    private MessageInput messageInput;
    private Section sidebar;
    protected BeanValidationBinder<Tamagotchi> binder;

    private TextField name;
    private IntegerField hunger;
    private IntegerField cleanliness;
    private IntegerField weight;
    private IntegerField energy;
    private IntegerField health;
    private IntegerField level;
    private IntegerField experience;
    private IntegerField age;
    private Checkbox sleeping;
    private Checkbox alive;
    private MultiSelectComboBox<String> personality;
    private MultiSelectComboBox<String> strengths;
    private MultiSelectComboBox<String> weaknesses;

    @Inject
    ChatLogsService chatLogsService;

    public TamagotchiChat() {
        setSpacing(false);
        timer = UIUtils.getTimer(this, 1);

        paragraph = new Paragraph();

        emotionEmoji = new H1();
        emotionEmoji.setWidth("200px");

        messageInput = new MessageInput(e -> sendMessage(e.getValue()));
        messageInput.setWidthFull();
        add(messageInput);

        VerticalLayout verticalLayout = new VerticalLayout(timer, paragraph, emotionEmoji, messageInput);
        verticalLayout.setSizeFull();
        verticalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.getStyle().set("text-align", "center");

        createSidebar();
        add(verticalLayout, sidebar);
        openSidebar();

        setSizeFull();
    }

    private void sendMessage(String message) {
        String answ = chatLogsService.sendMessage(adult, tamagotchi, message);
        paragraph.setText(answ);
    }

    @Override
    @Transactional
    public void propertyChange(PropertyChangeEvent event) {
        tamagotchi.timePassed();
        emotionEmoji.setText(tamagotchi.currentEmotion);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        User user = SecurityUtils.getLoggedUser();
        this.adult = Adult.findById(user.currentAdultId);
        this.tamagotchi = Tamagotchi.findById(adult.tamagotchiId);

        // init llm with background

        // salute
        emotionEmoji.setText(tamagotchi.currentEmotion);
        paragraph.setText("hallo!!! wie gehts dir?");
        timer.start();

        binder = new BeanValidationBinder<>(Tamagotchi.class);
        binder.bindInstanceFields(this);
        binder.setBean(tamagotchi);

    }

    private Section createSidebar() {
        H2 title = new H2("Status");
        title.addClassNames(FontSize.MEDIUM);

        Button close = new Button(LineAwesomeIcon.TIMES_SOLID.create(), e -> closeSidebar());
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.setTooltipText("Close sidebar");

        Layout header = new Layout(title, close);
        header.addClassNames(Padding.End.MEDIUM, Padding.Start.LARGE, Padding.Vertical.SMALL);
        header.setAlignItems(Layout.AlignItems.CENTER);
        header.setJustifyContent(Layout.JustifyContent.BETWEEN);

        name = UIUtils.getTextField("Tamagotchi.name", false, true);
        hunger = UIUtils.getIntegerField("Tamagotchi.hunger", false, true);
        cleanliness = UIUtils.getIntegerField("Tamagotchi.cleanliness", false, true);
        weight = UIUtils.getIntegerField("Tamagotchi.weight", false, true);
        energy = UIUtils.getIntegerField("Tamagotchi.energy", false, true);
        health = UIUtils.getIntegerField("Tamagotchi.health", false, true);
        level = UIUtils.getIntegerField("Tamagotchi.level", false, true);
        experience = UIUtils.getIntegerField("Tamagotchi.experience", false, true);
        age = UIUtils.getIntegerField("Tamagotchi.age", false, true);
        sleeping = UIUtils.getCheckbox("Tamagotchi.sleeping", true);
        alive = UIUtils.getCheckbox("Tamagotchi.alive", true);
        personality = UIUtils.getMultiSelectComboBox("tamagotchi.personality",
                "Tamagotchi.personality", false);
        personality.setReadOnly(true);
        strengths = UIUtils.getMultiSelectComboBox("tamagotchi.strengths",
                "Tamagotchi.strengths", false);
        strengths.setReadOnly(true);
        weaknesses = UIUtils.getMultiSelectComboBox("tamagotchi.weaknesses",
                "Tamagotchi.weaknesses", false);
        weaknesses.setReadOnly(true);

        Layout form = new Layout(name, hunger, cleanliness, weight, energy, health, level, age, new Span(), sleeping,
                alive,
                personality, strengths, weaknesses);

        // Viewport < 1024px
        form.setFlexDirection(Layout.FlexDirection.COLUMN);
        // Viewport > 1024px
        form.setDisplay(Breakpoint.LARGE, Layout.Display.GRID);
        form.setColumns(Layout.GridColumns.COLUMNS_2);
        form.setColumnGap(Layout.Gap.MEDIUM);
        form.setColumnSpan(Layout.ColumnSpan.COLUMN_SPAN_FULL, name, personality, strengths, weaknesses);

        Button eat = new Button("Essens", LineAwesomeIcon.COOKIE_BITE_SOLID.create());
        eat.addClickListener(e -> tamagotchi.eat());
        Button play = new Button("Spielen", LineAwesomeIcon.GUITAR_SOLID.create());
        play.addClickListener(e -> tamagotchi.play(5));
        Button wakeUp = new Button("Aufwachen", LineAwesomeIcon.CLOCK.create());
        wakeUp.addClickListener(e -> tamagotchi.wakeUp());
        Button sleep = new Button("Schlafen", LineAwesomeIcon.BEER_SOLID.create());
        sleep.addClickListener(e -> tamagotchi.sleep());
        Button clean = new Button("Sauber machen", LineAwesomeIcon.BATH_SOLID.create());
        clean.addClickListener(e -> tamagotchi.clean());

        Layout actionLayout = new Layout(eat, play, wakeUp, sleep, clean);

        // Viewport < 1024px
        actionLayout.setFlexDirection(Layout.FlexDirection.COLUMN);
        // Viewport > 1024px
        actionLayout.setDisplay(Breakpoint.LARGE, Layout.Display.GRID);
        actionLayout.setColumns(Layout.GridColumns.COLUMNS_3);
        actionLayout.setColumnGap(Layout.Gap.MEDIUM);

        this.sidebar = new Section(header, form, actionLayout);
        this.sidebar.addClassNames("backdrop-blur-sm", "bg-tint-90", Border.LEFT,
                Display.FLEX, FlexDirection.COLUMN, Position.ABSOLUTE, "lg:static", "bottom-0", "top-0",
                "transition-all", "z-10");
        this.sidebar.setWidth(40, Unit.REM);
        return this.sidebar;
    }

    private void closeSidebar() {
        this.sidebar.setEnabled(false);
        this.sidebar.removeClassName(Border.LEFT);
        // Desktop
        this.sidebar.getStyle().set("margin-inline-start", "-20rem");
        // Mobile
        this.sidebar.addClassNames("-start-full");
        this.sidebar.removeClassName("start-0");
    }

    private void openSidebar() {
        this.sidebar.setEnabled(true);
        this.sidebar.addClassNames(Border.LEFT);
        // Desktop
        this.sidebar.getStyle().remove("margin-inline-start");
        // Mobile
        this.sidebar.addClassNames("start-0");
        this.sidebar.removeClassName("-start-full");
    }

}
