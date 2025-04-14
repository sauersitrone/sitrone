package de.simone.ui.login;

import com.vaadin.flow.component.textfield.TextField;

import de.simone.UIUtils;
import de.simone.backend.AuthenticationResult;

public class ConfirmIdentity extends GALoginForm {

    private TextField totpField;

    public ConfirmIdentity(ExternalLogin login) {
        super(login);
        this.totpField = UIUtils.getTextField("ConfirmIdentity.totpField");
        this.totpField.setWidthFull();

        configureBody(totpField);
    }

    @Override
    public void accept() {
        AuthenticationResult result = externalLogin.externalLoginService.confirmIdentity(externalLogin.currentUser,
                totpField.getValue());
        if (result.isSuccess)
            externalLogin.setCurrentLoginForm(new ResetPassword(externalLogin));
        else
            UIUtils.showAuthenticationResultInFields(result, totpField);

    }
}
