package de.simone.ui;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import de.simone.UIUtils;
import de.simone.backend.Mailing;
import io.quarkus.mailer.Mail;

public class EMailPreview extends VerticalLayout {

    public TextField from = new TextField("From");
    public TextField to = new TextField("To");
    public TextField subject = new TextField("Subject");
    public TextArea bodyText = new TextArea("Body");
    public Span bodyHtml = new Span();
    private VerticalLayout container;
    private String mailType;

    public EMailPreview() {
        setSpacing(false);
        setMargin(false);
        from.setReadOnly(true);
        to.setReadOnly(true);
        subject.setReadOnly(true);
        bodyText.setReadOnly(true);
        this.mailType = Mailing.HTML;
        this.container = UIUtils.getCompactVerticalLayout(from, to, subject, bodyHtml, bodyText);
        container.setAlignItems(FlexComponent.Alignment.STRETCH);

        Scroller scroller = new Scroller(container);
        scroller.setHeightFull();
        H3 header = new H3(getTranslation("EMailPreview.title"));
        VerticalLayout footer = UIUtils.getCompactVerticalLayout(UIUtils.getHrSeparator(),
                UIUtils.getSecondarySmallLabel("EMailPreview.message"));
        add(header, scroller, footer);
        setAlignItems(FlexComponent.Alignment.STRETCH);
        UIUtils.setGroupStyle(this);

    }

    public EMailPreview(Mail mail) {
        this();
        setMail(mail);
    }

    public void switchTo(String newType) {
        this.mailType = newType;
        bodyHtml.setVisible(Mailing.HTML.equals(newType));
        bodyText.setVisible(Mailing.TEXT.equals(newType));
    }

    public void setMail(Mail mail) {
        from.setValue(mail.getFrom());
        to.setValue(mail.getTo().toString());
        subject.setValue(mail.getSubject() == null ? "" : mail.getSubject());
        // html or text
        if (Mailing.HTML.equals(mailType)) {
            bodyHtml.getElement().setProperty("innerHTML", mail.getHtml() == null ? "" : mail.getHtml());
        } else {
            bodyText.setValue(mail.getText() == null ? "" : mail.getText());
        }
    }
}
