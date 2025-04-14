package de.simone.ui.login;

import com.vaadin.flow.component.textfield.EmailField;

import de.simone.UIUtils;
import de.simone.backend.User;

public class PasswordRecover extends GALoginForm {

    private EmailField emailField;

    public PasswordRecover(ExternalLogin login) {
        super(login);
        this.emailField = UIUtils.getEmailField("PasswordRecover.emailField");

        configureBody(emailField);
    }

    @Override
    public void accept() {
        User user = User.find("email = ?1", emailField.getValue()).firstResult();
        if (user == null) {
            UIUtils.showErrorForField(emailField, "PasswordRecover.email.notfound");
            return;
        }

        externalLogin.currentUser = user;
        externalLogin.setCurrentLoginForm(new PasswordRecoverOptions(externalLogin));
    }
}
