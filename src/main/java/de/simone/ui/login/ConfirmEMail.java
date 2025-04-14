package de.simone.ui.login;

import com.vaadin.flow.component.textfield.IntegerField;

import de.simone.UIUtils;
import de.simone.backend.AuthenticationResult;

public class ConfirmEMail extends GALoginForm {

    private IntegerField totpField;

    public ConfirmEMail(ExternalLogin login) {
        super(login);
        this.totpField = UIUtils.getIntegerField("ConfirmEMail.totpField");
        this.totpField.setWidthFull();

        configureBody(totpField);
    }

    @Override
    public void accept() {
        AuthenticationResult result = externalLogin.externalLoginService
                .confirmEmail(externalLogin.currentUser, totpField.getValue().toString());
        if (result.isSuccess)
            externalLogin.autenticate();
        else
            UIUtils.showAuthenticationResultInFields(result, totpField);
    }
}
