package de.simone.ui.login;

public class LoginSessionExpired extends GALoginForm {

    public LoginSessionExpired(ExternalLogin login) {
        super(login);
        allowBack = false;
        configureBody();
    }

    @Override
    public void accept() {
        externalLogin.resetSession();
    }
}
