package de.simone.ui;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.MessagingProvider;
import de.simone.backend.MessagingProvidersService;
import de.simone.backend.User;
import de.simone.ui.components.FileLoaderSimple;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.inject.spi.CDI;

@RolesAllowed({ "Zitrone.master", "User.edit" })
@Route(value = "User", layout = MainLayout.class)
public class UserForm extends TAForm<User>  {

    private TextField firstName;
    private TextField lastName;
    private TextField createdAt2;
    private TextField phone;
    private TextField userName;
    private EmailField email;
    private Checkbox isEnabled;
    private Checkbox isEmailVerified;
    private Checkbox isMfaExempt;
    private Checkbox isTemporalPass;
    private Checkbox isTotpRegistred;
    private Select<String> preferredLanguage;
    private Select<MessagingProvider> messagingProvider;
    private Select<String> status;
    private FileLoaderSimple avatar;

    public UserForm() {
        avatar = new FileLoaderSimple("User.avatar");
        firstName = UIUtils.getTextField("User.firstName");
        lastName = UIUtils.getTextField("User.lastName");
        email = UIUtils.getEmailField("User.email");
        phone = UIUtils.getTextField("User.phone");
        phone.setRequiredIndicatorVisible(false);
        userName = UIUtils.getTextField("User.userName");
        isEnabled = UIUtils.getCheckbox("User.isEnabled");
        VerticalLayout isEnabledWrap = UIUtils.getWrapForCheckBox(isEnabled);
        isEmailVerified = UIUtils.getCheckbox("User.isEmailVerified");
        VerticalLayout isEmailVerifiedWrap = UIUtils.getWrapForCheckBox(isEmailVerified);
        isMfaExempt = UIUtils.getCheckbox("User.isMfaExempt");
        VerticalLayout isMfaExemptWrap = UIUtils.getWrapForCheckBox(isMfaExempt);
        isTotpRegistred = UIUtils.getCheckbox("User.isTotpRegistred");
        VerticalLayout isTotpRegistredWrap = UIUtils.getWrapForCheckBox(isTotpRegistred);
        isTemporalPass = UIUtils.getCheckbox("User.isTemporalPass");
        VerticalLayout isTemporalPassWrap = UIUtils.getWrapForCheckBox(isTemporalPass);
        preferredLanguage = UIUtils.getSelect("languages", "User.preferredLanguage");
        messagingProvider = UIUtils.getMessagingProviderSelect("User.messagingProvider", false, null);
        createdAt2 = UIUtils.getTextField("TAEntity.createdAt");
        createdAt2.setReadOnly(true);
        status = UIUtils.getSelect("user.status", "User.status");

        addBodyComponets("separator.general", false,  firstName, lastName, userName, new HorizontalLayout(email, phone), messagingProvider,preferredLanguage, avatar);
        addBodyComponets("user.separator2", false,  isEnabledWrap, isEmailVerifiedWrap, isMfaExemptWrap, isTotpRegistredWrap,
                isTemporalPassWrap);

    }

    @Override
    public void setEntity(User entity) {
            MessagingProvidersService   messagingProvidersService = CDI.current().select(MessagingProvidersService.class).get();
            messagingProvider.setItems(messagingProvidersService.listAll(Sort.ascending("provider")));
            super.setEntity(entity);
    }
}
