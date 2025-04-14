package de.simone.ui.login;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import de.simone.UIUtils;
import de.simone.backend.AuthenticationResult;
import de.simone.backend.User;

public class UserRegistration extends GALoginForm {

    private TextField firstName;
    private TextField lastName;
    private TextField userName;
    private EmailField email;
    private PasswordField password;
    private PasswordField confirmPasswordField;
    private BeanValidationBinder<User> binder;

    public UserRegistration(ExternalLogin login) {
        this(login, new User());
    }

    public UserRegistration(ExternalLogin login, User user) {
        super(login);
        this.firstName = UIUtils.getTextField("User.firstName");
        this.lastName = UIUtils.getTextField("User.lastName");
        this.email = UIUtils.getEmailField("User.email");
        this.password = UIUtils.getPasswordField("User.password");
        this.confirmPasswordField = UIUtils.getPasswordField("User.passwordConfirmation");
        this.userName = UIUtils.getTextField("User.userName");

        FormLayout formLayout = new FormLayout(firstName, lastName, userName, email, password,
                confirmPasswordField);

        formLayout.setColspan(userName, 2);
        formLayout.setColspan(email, 2);
        formLayout.setResponsiveSteps(new ResponsiveStep("0", 2));

        configureBody(formLayout);

        this.binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);
        binder.setBean(user);
    }

    @Override
    public Component[] getFooter() {
        return new Component[] { button };
    }

    @Override
    public void accept() {
        User user = binder.getBean();
        if (!binder.validate().isOk())
            return;

        if (!UIUtils.confirmPasswordFields(null, password, confirmPasswordField))
            return;

        AuthenticationResult result = externalLogin.externalLoginService.createNewUser(user, password.getValue());
        if (result.isSuccess) {
            externalLogin.currentUser = result.user;
            externalLogin.plainPassword = password.getValue();

            externalLogin.setCurrentLoginForm(new ConfirmEMail(externalLogin));
        } else {
            if (result.result.equals(AuthenticationResult.FAILED_PASSWORD_POLICY)) {
                UIUtils.showAuthenticationResultInFields(result, password, confirmPasswordField);
            } else
                UIUtils.showAuthenticationResultInFields(result, userName, email);
        }
    }
}
