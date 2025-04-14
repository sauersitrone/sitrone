package de.simone.ui;

import java.util.Collections;
import java.util.List;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.ui.LoadMode;

import de.simone.GGroupProperty;
import de.simone.TranslationProvider;
import de.simone.UIUtils;
import de.simone.backend.MiscellaneousService;
import io.quarkus.logging.Log;
import jakarta.servlet.http.HttpServletResponse;

@Route("Hello404")
@StyleSheet("hello404Effect.css")
public class Hello404 extends VerticalLayout implements HasErrorParameter<NotFoundException> {

    private VerticalLayout inputArea;
    private Anchor homeAnchor;

    public Hello404() {
        setSizeFull();
        setPadding(false);
        getStyle().set("background", "rgba(52,52,54)"); // the same color as the last wave in Hello404Effect.html

        this.inputArea = UIUtils.getInputOuputArea();
        inputArea.getStyle().set("background", "hsla(0, 100%, 100%, 0.85)");
        inputArea.setPadding(true);
        inputArea.setAlignItems(FlexComponent.Alignment.CENTER);



        Select<String> languageSelect = UIUtils.getLanguageSelect();
        // languageSelect.getStyle().set("color", "white");
        languageSelect.addClassName("hello404");

        homeAnchor =UIUtils.getHomePageButton();

        Anchor anchor1 = new Anchor("https://www.gooddev.de/", getTranslation("aditionalLink.userContitions"));
        anchor1.getStyle().set("color", "lightgray");
        Anchor anchor2 = new Anchor("https://www.gooddev.de/", getTranslation("aditionalLink.data"));
        anchor2.getStyle().set("color", "lightgray");
        Anchor anchor4 = new Anchor("https://www.gooddev.de/", getTranslation("aditionalLink.support"));
        anchor4.getStyle().set("color", "lightgray");

        HorizontalLayout footer = new HorizontalLayout(anchor1, anchor2, anchor4);
        footer.getStyle().set("background", "rgba(55,55,55,0.1)"); // the same color as the last wave in
                                                                   // Hello404Effect.html

        Span background = new Span();
        background.setSizeFull();
        String html = MiscellaneousService.readScriptFile("Hello404Effect.html");
        background.getElement().setProperty("innerHTML", html);
        background.getStyle().set("z-index", "2").set("position", "relative");

        VerticalLayout fronLayout = new VerticalLayout(inputArea, languageSelect, footer);
        fronLayout.getStyle().set("z-index", "3").set("position", "absolute");
        fronLayout.setSizeFull();
        fronLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        fronLayout.setAlignItems(FlexComponent.Alignment.END);

        add(background, fronLayout);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UI.getCurrent().getPage().addJavaScript("/Hello404Effect.js", LoadMode.LAZY);

        // randomly select a 404 msg
        List<GGroupProperty> msgs = TranslationProvider.getGroupProperties("hello.404");
        Collections.shuffle(msgs);
        Span span = UIUtils.getSpan(msgs.get(0).value, false);

        inputArea.add(span, homeAnchor);
    }

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        Log.warn("404: " + parameter.getCustomMessage() + " Cauth exception: "
                + parameter.getCaughtException().getMessage());
        return HttpServletResponse.SC_NOT_FOUND;
    }
}
