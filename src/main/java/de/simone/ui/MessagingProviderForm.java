package de.simone.ui;

import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.MessagingProvider;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({"Zitrone.master", "MessagingProvider.edit"})
@Route(value = "MessagingProvider", layout = MainLayout.class)
public class MessagingProviderForm extends TAForm<MessagingProvider> {

  private Select<String> provider;
  private TextField accountName;
  private TextField clientId;
  private TextField secret;
  private TextField clientIdTest;
  private TextField secretTest;

  public MessagingProviderForm() {
    provider = UIUtils.getSelect("messagingProvider.provider", "MessagingProvider.provider");
    accountName = UIUtils.getTextField("MessagingProvider.accountName");
    clientId = UIUtils.getTextField("MessagingProvider.clientId");
    secret = UIUtils.getTextField("MessagingProvider.secret");
    clientIdTest = UIUtils.getTextField("MessagingProvider.clientIdTest");
    secretTest = UIUtils.getTextField("MessagingProvider.secretTest");
    addBodyComponets(provider, accountName);
    addBodyComponets("messagingProvider.separator1", clientId, secret);
    addBodyComponets("messagingProvider.separator2", clientIdTest, secretTest);
  }

  @Override
  public void setEntity(MessagingProvider entity) {
    boolean noEdit = !entity.isNewEntity();
    provider.setReadOnly(noEdit);
    
    super.setEntity(entity);
  }
}
