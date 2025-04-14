package de.simone.ui.login;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.textfield.PasswordField;

import de.simone.UIUtils;
import de.simone.backend.AuthenticationResult;
import de.simone.backend.GlobalProperty;

public class LogInWithGoodMfa extends GALoginForm {

    private PasswordField password;
    private Button forgotPass;

    public LogInWithGoodMfa(ExternalLogin login) {
        super(login);
        this.forgotPass = UIUtils.getButton("LogInWithGoodMfa.forgotPass",
                ButtonVariant.LUMO_TERTIARY_INLINE);
        forgotPass.addClickListener(evt -> forgotPass());

        this.password = UIUtils.getPasswordField("LogInWithGoodMfa.password");
        password.setErrorMessage("getTitle()");

        configureBody(password);
    }

    @Override
    public Component[] getFooter() {
        boolean showForgotPass = GlobalProperty.getInstance().forgotPassword;
        return showForgotPass ? new Component[] { forgotPass, button } : new Component[] { button };
    }

    private void forgotPass() {
        externalLogin.setCurrentLoginForm(new PasswordRecover(externalLogin));
    }

    @Override
    public void accept() {
        AuthenticationResult result = externalLogin.externalLoginService.autenticate(externalLogin.userName,
                password.getValue(), UI.getCurrent().getUIId());

        if (!result.isSuccess) {
            UIUtils.showAuthenticationResultInFields(result, password);
            return;
        }
        externalLogin.currentUser = result.user;
        externalLogin.plainPassword = password.getValue();
        externalLogin.autenticate();
    }
}
