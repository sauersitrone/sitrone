package de.simone.ui.login;

import com.vaadin.flow.component.textfield.PasswordField;

import de.simone.UIUtils;
import jakarta.ws.rs.core.Response;


public class ResetPassword extends GALoginForm {

    private PasswordField newPassword;
    private PasswordField confirmPassword;

    public ResetPassword(ExternalLogin login) {
        super(login);
        allowBack = false;
        this.newPassword = UIUtils.getPasswordField("ResetPassword.newPassword");
        this.confirmPassword = UIUtils.getPasswordField("ResetPassword.confirmPassword");
        configureBody(newPassword, confirmPassword);
    }

    @Override
    public void accept() {
        if (UIUtils.confirmPasswordFields(null, newPassword, confirmPassword)) {
            Response response = externalLogin.usersService.setNewPassword(externalLogin.currentUser,
                    newPassword.getValue(), false);
            if (UIUtils.showResponseInFields(response, newPassword, confirmPassword)) {
                externalLogin.plainPassword = newPassword.getValue();
                externalLogin.autenticate();
            }
        }
    }
}
