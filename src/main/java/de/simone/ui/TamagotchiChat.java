package de.simone.ui;

import org.vaadin.lineawesome.*;

import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.messages.*;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.dom.*;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.*;

import de.simone.*;
import de.simone.backend.*;
import de.simone.ui.components.*;
import jakarta.inject.*;
import jakarta.transaction.*;

@AnonymousAllowed
@Route(value = "TamagotchiChat", layout = MainLayout.class)
public class TamagotchiChat extends VerticalLayout
    implements BeforeEnterObserver, PropertyChangeListener {

  private H1 emotionEmoji;
  private Paragraph paragraph;
  private Adult adult;
  private Tamagotchi tamagotchi;
  private TTimer timer; 
  private Span status;
  private MessageInput messageInput;

  @Inject
ChatLogsService chatLogsService;

  public TamagotchiChat() {
    setSpacing(false);
    timer = UIUtils.getTimer(this, 1);
    add(timer);

    paragraph = new Paragraph();
    add(paragraph);

    emotionEmoji = new H1();
    emotionEmoji.setWidth("200px");
    add(emotionEmoji);

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
    HorizontalLayout horizontalLayout = new HorizontalLayout(eat, play, wakeUp, sleep, clean);
    add(horizontalLayout);
 
    status = new Span();
    add(status);

    messageInput = new MessageInput(e -> sendMessage(e.getValue()));
    messageInput.setWidthFull();
    add(messageInput);

    setSizeFull();
    setJustifyContentMode(JustifyContentMode.CENTER);
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    getStyle().set("text-align", "center");
  }

  private void sendMessage(String message) {
    String answ = chatLogsService.sendMessage(adult.id, message);
    paragraph.setText(answ);
  }

  @Override
  @Transactional
  public void propertyChange(PropertyChangeEvent event) {
    tamagotchi.timePassed();
    status.getElement().setProperty("innerHTML", tamagotchi.toHtmlString());
    emotionEmoji.setText(tamagotchi.currentEmotion);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    this.adult = Adult.findById(199268075270145L);
    this.tamagotchi = Tamagotchi.findById(adult.tamagotchiId);

    // init llm with background

    // salute
    emotionEmoji.setText(tamagotchi.currentEmotion);
    paragraph.setText("hallo!!! wie gehts dir?");
    timer.start();
  }
}
