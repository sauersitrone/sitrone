package de.simone.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.PropertyChangeEvent;
import com.vaadin.flow.dom.PropertyChangeListener;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.Adult;
import de.simone.backend.Tamagotchi;
import de.simone.ui.components.TTimer;
import jakarta.transaction.Transactional;
import org.vaadin.lineawesome.LineAwesomeIcon;

@AnonymousAllowed
@Route(value = "TamagotchiChat", layout = MainLayout.class)
public class TamagotchiChat extends VerticalLayout
    implements PropertyChangeListener, HasUrlParameter<Long> {

  private H1 emotionEmoji;
  private Paragraph paragraph;
  private Adult adult;
  private Tamagotchi tamagotchi;
  private TTimer timer;
  private Span status;

  public TamagotchiChat() {
    setSpacing(false);
    timer = UIUtils.getTimer(this, 1);
    add(timer);

    emotionEmoji = new H1();
    emotionEmoji.setWidth("200px");
    add(emotionEmoji);

    paragraph = new Paragraph();
    add(paragraph);

    Button eat = new Button("Essen", LineAwesomeIcon.COOKIE_BITE_SOLID.create());
    eat.addClickListener(e -> tamagotchi.eat());
    Button play = new Button("Spielen", LineAwesomeIcon.GUITAR_SOLID.create());
    play.addClickListener(e -> tamagotchi.play(5));
    Button wakeUp = new Button("Aufwachen", LineAwesomeIcon.CLOCK.create());
    wakeUp.addClickListener(e -> tamagotchi.wakeUp());
    Button sleep = new Button("Schlafen", LineAwesomeIcon.BEER_SOLID.create());
    sleep.addClickListener(e -> tamagotchi.sleep());
    Button clean = new Button("Sauber machen", LineAwesomeIcon.BATH_SOLID.create());
    clean.addClickListener(e -> tamagotchi.clean());
    HorizontalLayout horizontalLayout = new HorizontalLayout(eat, play, wakeUp, sleep, clean);
    add(horizontalLayout);

    status = new Span();
    add(status);

    setSizeFull();
    setJustifyContentMode(JustifyContentMode.CENTER);
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    getStyle().set("text-align", "center");
  }

  @Override
  public void setParameter(BeforeEvent event, Long parameter) {
    this.adult = Adult.findById(parameter);
    this.tamagotchi = Tamagotchi.findById(adult.tamagotchiId);

    // init llm with background

    // salute
    emotionEmoji.setText(tamagotchi.currentEmotion);
    paragraph.setText("hallo!!! wie gehts dir?");
    timer.start();
  }

  @Override
  @Transactional
  public void propertyChange(PropertyChangeEvent event) {
    tamagotchi.timePassed();
    status.getElement().setProperty("innerHTML", tamagotchi.toHtmlString());
    emotionEmoji.setText(tamagotchi.currentEmotion);

  }
}
