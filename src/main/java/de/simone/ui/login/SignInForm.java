package de.simone.ui.login;

import java.util.ArrayList;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.ui.LoadMode;

import de.simone.UIUtils;
import de.simone.backend.AuthenticationResult;
import de.simone.backend.GlobalProperty;

public class SignInForm extends GALoginForm {

    private TextField userName;

    public SignInForm(ExternalLogin login) {
        super(login);
        setId(this.getClass().getName());
        allowBack = false;
        ArrayList<Component> components = new ArrayList<>();
        GlobalProperty globalProperty = GlobalProperty.getInstance();

        userName = UIUtils.getTextField("LogInWithGoodMfa.userOrEmail");
        userName.setId("userName");
        components.add(userName);
        userName.addValueChangeListener(evt -> button.setEnabled(!evt.getValue().equals("")));

        Button withPassword = new Button(UIUtils.getLaIcon("las la-key"));
        withPassword.setText(getTranslation("SignInForm.whitPassword"));
        withPassword.addClickListener(evt -> accept());
        components.add(withPassword);

        Button withPassKey = new Button(UIUtils.getLaIcon("las la-fingerprint"));
        withPassKey.setText(getTranslation("SignInForm.whitPassKey"));
        withPassKey.addClickListener(e -> accept2());
        components.add(withPassKey);

        // allow new user registration?
        if (globalProperty.enableUserRegistration) {
            components.add(new Span(" "));
            components.add(UIUtils.getOrSeparator("or.separator"));
            components.add(new Span(" "));
            // create account
            Button creataAccount = UIUtils.getButton("SignInForm.create.button", ButtonVariant.LUMO_PRIMARY);
            creataAccount.addClickListener(evt -> createAccount());
            components.add(creataAccount);
        }

        configureBody(components.toArray(new Component[0]));
        setSpacing(true);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UI.getCurrent().getPage().addJavaScript("/webauthn.js", LoadMode.LAZY);
    }

    @ClientCallable
    public void logInStaus(String message) {
        if (message.startsWith("ok.")) {
            AuthenticationResult result = externalLogin.externalLoginService.getUser(userName.getValue());
            if (result.isSuccess) {
                externalLogin.currentUser = result.user;
                externalLogin.plainPassword = result.user.userSecret;
                externalLogin.autenticate();
            }
        } else
            UIUtils.showErrorNotification("passKey.error", message);
    }

    @Override
    public Component[] getFooter() {
        return new Component[0];
    }

    private void createAccount() {
        externalLogin.setCurrentLoginForm(new UserRegistration(externalLogin));
    }

    private void accept2() {
        AuthenticationResult result = externalLogin.externalLoginService.getUser(userName.getValue());
        if (!result.isSuccess) {
            UIUtils.showAuthenticationResultInFields(result, userName);
            return;
        }

        // check if the user hat configured keypas
        // WebAuthnCredential credential = WebAuthnCredential.find("userName = ?1", result.user.userName).firstResult();
        // if (credential == null) {
        //     userName.setErrorMessage(getTranslation("Response.noWebauth", userName.getValue()));
        //     userName.setInvalid(true);
        //     return;
        // }

        // alles kla? login
        getElement().executeJs("logInFromVaadin($0)", result.user.userName);
    }

    @Override
    public void accept() {
        AuthenticationResult result = externalLogin.externalLoginService.getUser(userName.getValue());
        if (!result.isSuccess) {
            UIUtils.showAuthenticationResultInFields(result, userName);
            return;
        }

        externalLogin.userName = userName.getValue();
        externalLogin.setCurrentLoginForm(new LogInWithGoodMfa(externalLogin));
    }

}
