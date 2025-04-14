package de.simone.ui.login;

import java.util.List;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.simone.UIUtils;
import de.simone.backend.AuthenticationResult;
import de.simone.backend.ResetOption;

public class PasswordRecoverOptions extends GALoginForm {

    private ListBox<ResetOption> listBox;

    public PasswordRecoverOptions(ExternalLogin login) {
        super(login);
        AuthenticationResult result = externalLogin.externalLoginService.getResetOptions(externalLogin.currentUser);

        button.setEnabled(false);
        listBox = new ListBox<>();
        @SuppressWarnings("unchecked")
        List<ResetOption> opts = (List<ResetOption>) result.payload;
        listBox.setItems(opts);
        listBox.addValueChangeListener(evt -> button.setEnabled(evt.getValue() != null));
        listBox.addClassNames(LumoUtility.Border.ALL, LumoUtility.BorderColor.CONTRAST_20);
        listBox.setHeightFull();
        // listBox.setRenderer(new ComponentRenderer<>(rOption -> {
        //     return new ListItem(rOption.name, rOption.description);
        // }));

        configureBody(listBox);
    }

    @Override
    public void accept() {
        ResetOption option = listBox.getValue();
        AuthenticationResult result =
                externalLogin.externalLoginService.sendConfirmationCode(externalLogin.currentUser, option.action);
        if (result.isSuccess)
            externalLogin.setCurrentLoginForm(new ConfirmIdentity(externalLogin));
        else
            UIUtils.showErrorNotification(result.result);

    }
}
