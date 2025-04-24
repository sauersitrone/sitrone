package de.simone.ui.login;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.page.WebStorage;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.dom.PropertyChangeEvent;
import com.vaadin.flow.dom.PropertyChangeListener;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.mekaso.vaadin.addon.compani.animation.AnimationBuilder;
import de.mekaso.vaadin.addon.compani.animation.AnimationTypes;
import de.mekaso.vaadin.addon.compani.animation.AnimationTypes.ExitAnimation;
import de.mekaso.vaadin.addon.compani.effect.Delay;
import de.mekaso.vaadin.addon.compani.effect.EntranceEffect;
import de.mekaso.vaadin.addon.compani.effect.ExitEffect;
import de.mekaso.vaadin.addon.compani.effect.Speed;
import de.simone.SecurityUtils;
import de.simone.UIUtils;
import de.simone.Sitrone;
import de.simone.backend.AuditLog;
import de.simone.backend.AuthenticationResult;
import de.simone.backend.ExternalLoginService;
import de.simone.backend.GlobalProperty;
import de.simone.backend.MailingsService;
import de.simone.backend.MiscellaneousService;
import de.simone.backend.ResetOption;
import de.simone.backend.ResetToken;
import de.simone.backend.User;
import de.simone.backend.UsersService;
import de.simone.ui.components.Card;
import de.simone.ui.components.TTimer;
import io.quarkus.logging.Log;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;

@Route("login")
@PermitAll
// @Stylesheet(Animation.STYLES)
@StyleSheet("loginEffect.css")
public class ExternalLogin extends VerticalLayout 
        implements BeforeEnterObserver, PropertyChangeListener { 

    @Inject
    ExternalLoginService externalLoginService;
    @Inject
    UsersService usersService;
    @Inject
    MailingsService mailingsService;

    User currentUser;
    String plainPassword;
    String userName;

    private List<GALoginForm> loginForms;
    private TTimer timer;
    private ObjectMapper objectMapper = new ObjectMapper();
    private VerticalLayout inputArea;
    private int loginActionTimeout;
    private String defaultPassword;
    private int currentWith;
    private Card card;

    private Span background;
    private VerticalLayout fronLayout;

    public ExternalLogin() {
        setSizeFull();
        setPadding(false);
        this.loginForms = new ArrayList<>();
        this.inputArea = UIUtils.getInputOuputArea();
        this.timer = UIUtils.getTimer(this, 2);

        resetSession();
        Select<String> languageSelect = UIUtils.getLanguageSelect();

        Span versioLabel = new Span(Sitrone.getInstance().version);
        versioLabel.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.FontSize.XSMALL);

        Anchor anchor1 = new Anchor("https://www.gooddev.de/", getTranslation("aditionalLink.userContitions"));
        Anchor anchor2 = new Anchor("https://www.gooddev.de/", getTranslation("aditionalLink.data"));
        Anchor anchor4 = new Anchor("https://www.gooddev.de/", getTranslation("aditionalLink.support"));
        HorizontalLayout footer = new HorizontalLayout(anchor1, anchor2, anchor4);

        background = new Span();
        background.setSizeFull();
        String html = MiscellaneousService.readScriptFile("loginEffect.html");
        background.getElement().setProperty("innerHTML", html);
        background.getStyle().set("z-index", "2").set("position", "relative");

        fronLayout = new VerticalLayout(inputArea, languageSelect, footer, versioLabel, timer);
        fronLayout.getStyle().set("z-index", "3").set("position", "absolute");
        fronLayout.setSizeFull();
        fronLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        fronLayout.setAlignItems(FlexComponent.Alignment.END);

        // add components are in beforeEnter method
    }

    protected void moveBack() {
        // remove current
        loginForms.remove(loginForms.size() - 1);
        // select previous
        GALoginForm loginForm = loginForms.remove(loginForms.size() - 1);
        setCurrentLoginForm(loginForm);
    }

    public void resetSession() {
        this.currentUser = null;
        this.plainPassword = null;
        setCurrentLoginForm(new SignInForm(this));
        GlobalProperty property = GlobalProperty.getInstance();
        loginActionTimeout = UIUtils.getSeconds(property.loginActionTimeout);
        defaultPassword = property.defaultPassword;
    }

    /**
     * this method perform the autentication procedure and set the correct secuence
     * of event acording to server side responce.
     */
    public void autenticate() {
        timer.reset();
        AuthenticationResult result = externalLoginService.autenticate(currentUser.email, plainPassword,
                UI.getCurrent().getUIId());

        // autenticate method change internal variables. reload
        this.currentUser = result.user;

        System.out.println("ExternalLogin " + result.result);
        System.out.println("ExternalLogin " + currentUser.getActions());

        // verify email
        if (currentUser.containAction(User.VERIFY_EMAIL)) {
            externalLoginService.sendConfirmationCode(currentUser, ResetOption.EMAIL_ACTION);
            setCurrentLoginForm(new ConfirmEMail(this));
            return;
        }

        // reset password
        if (currentUser.containAction(User.UPDATE_PASS)) {
            setCurrentLoginForm(new ResetPassword(this));
            return;
        }

        // success contain jwt. redirect to origen
        if (result.result.equals(AuthenticationResult.SUCCESS_FINAL)) {
            redirect(result);
        }
    }

    /**
     * final step from the loging process
     */
    private void redirect(AuthenticationResult result) {
        boolean authenticated = SecurityUtils.authenticate(result.user.userName, plainPassword);
        if (authenticated) {
            AuditLog.logLoggin();
            UI.getCurrent().getPage().setLocation("/");

        }
    }

    private void performRelogin(String value) {
        String[] userData = new String[2];
        // if no relogin, (value = null) check terry backdor
        if (value == null) {
            try (FileInputStream stream = new FileInputStream("TerryBackDoor.txt")) {
                String[] jaNeinUserPass = new String(stream.readAllBytes()).split(";");
                if ("Ja".equals(jaNeinUserPass[0])) {
                    userData[0] = jaNeinUserPass[1];
                    userData[1] = jaNeinUserPass[2];
                }
            } catch (Exception e) {
                // do nothing
            }
        } else {
            // retribe relogin info from SecurityUtils
            userData = SecurityUtils.getUserData(Long.valueOf(value));
            WebStorage.removeItem(SecurityUtils.GF_RELOGGIN);
        }

        // all set?

        if (StringUtils.isNotBlank(userData[0]) && StringUtils.isNotBlank(userData[1])) {
            boolean authenticated = SecurityUtils.authenticate(userData[0], userData[1]);
            if (authenticated) {
                AuditLog.logLoggin();
                UI.getCurrent().getPage().setLocation("/");
                return;
            }
        }

        // show login
        add(background, fronLayout);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        WebStorage.getItem(SecurityUtils.GF_RELOGGIN, this::performRelogin);
    }

    /**
     * perform the list of acction stored in magic link from reset credentials
     * e-mail
     * 
     * @param resetActionToken - encripted token in reset email link
     * @return
     */
    public void resetCredentials(String resetActionToken) {
        String payload = new String(Base64.getDecoder().decode(resetActionToken));
        try {
            ResetToken token = objectMapper.readValue(payload, ResetToken.class);
            int period = (int) Duration.parse(token.duration).getSeconds();
            // TODO: implement
            // AuthenticationResult result = externalLoginService.verifyTotp(1L, token.totp,
            // period);
            // if (!result.isSuccess) {
            UIUtils.showErrorNotification("externalLogin.reset-credential.expired");
            return;
            // }
            // for (String action : token.resetActions) {
            // // if (AuthConstans.UPDATE_PASS.equals(action))
            // // setResetPasswordBody();
            // // if (AuthConstans.CONFIGURE_OTP.equals(action))
            // // setConfigureTotpBody();
            // }
        } catch (Exception e) {
            Log.error("", e);
            UIUtils.showErrorNotification("externalLogin.error");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        int laps = timer.getCurrentTime().intValue();
        if (laps > loginActionTimeout && timer.isRunning()) {
            timer.pause();
            setCurrentLoginForm(new LoginSessionExpired(this));
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(ui -> {
            Page page = ui.getPage();
            page.retrieveExtendedClientDetails(details -> {
                currentWith = details.getBodyClientWidth();
                adjustMainLayoutSize();
            });
            page.addBrowserWindowResizeListener(event -> {
                currentWith = event.getWidth();
                adjustMainLayoutSize();
            });
        });
    }

    protected void setCurrentLoginForm(GALoginForm form) {
        timer.reset();

        // star the timer only after this form
        if (form instanceof SignInForm || form instanceof LoginSessionExpired) {
            timer.pause();
        } else {
            if (!timer.isRunning())
                timer.start();
        }

        Card oldCard = card;
        card = new Card();
        card.setSizeFull();
        form.setSizeFull();
        card.setHeader(form.getTitle());
        card.setBody(form);
        loginForms.add(form);

        adjustMainLayoutSize();

        if (form.getFooter().length > 0)
            card.setFooter(form.getFooter());
        else
            card.hideFooter();

        if (oldCard != null) {
            ExitAnimation exitAnimation = AnimationBuilder.createBuilderFor(oldCard)
                    .create(AnimationTypes.ExitAnimation.class).withEffect(ExitEffect.fadeOut);
            exitAnimation.addAnimationEndListener(ev -> {
                AnimationBuilder.createBuilderFor(card)
                        .create(AnimationTypes.EntranceAnimation.class).withSpeed(Speed.faster).withDelay(Delay.noDelay)
                        .withEffect(EntranceEffect.fadeIn).register();
                inputArea.add(card);
            });
            exitAnimation.remove();

        } else {
            inputArea.add(card);
        }
    }

    private void adjustMainLayoutSize() {
        boolean mobile = currentWith < UIUtils.MOBILE_BREAKPOINT;

        if (mobile) {
            inputArea.setSizeFull();
            inputArea.setWidth(null);
            inputArea.setHeight(null);
        } else {
            inputArea.setWidth(UIUtils.LOGGING_FORM_WITH);
            inputArea.setHeight(UIUtils.LOGGING_FORM_HEIGHT);
        }
    }
}
