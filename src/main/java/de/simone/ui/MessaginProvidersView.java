package de.simone.ui;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;

import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.MessagingProvider;
import de.simone.backend.MessagingProvidersService;
import de.simone.components.list.ListItem;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({"Zitrone.master", "MessaginProviders"})
@Route(value = "MessaginProviders", layout = MainLayout.class)
public class MessaginProvidersView extends TAView<MessagingProvider> {

  @Inject MessagingProvidersService messagingProvidersService;

  public MessaginProvidersView() {
    this.grid = UIUtils.getGrid(MessagingProvider.class);

    // mobile
    grid.addColumn(
            new ComponentRenderer<>(
                ge -> {
                  ListItem item = UIUtils.getListItem(ge.getName(), null, ge.getIcon());
                  return     item;
                }))
        .setHeader(getTranslation("MessagingProvider.name"))
        .setComparator(te -> te.getName())
        .setSortable(true)
        .setAutoWidth(true);

    // desktop
    UIUtils.setIdColumn(grid);

    grid.addColumn(new ComponentRenderer<>(UIUtils::getMessagingProviderRender))
        .setHeader(getTranslation("MessagingProvider.name"))
        .setComparator(te -> te.getName())
        .setSortable(true)
        .setAutoWidth(true);

  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    init(MessagingProvider.class, MessagingProviderForm.class, messagingProvidersService, true);

    SerializablePredicate<MessagingProvider> filter =
        entity -> {
          String searchTerm = searchField.getValue().trim().toLowerCase();
          if (searchTerm.isEmpty()) return true;

          boolean m1 = entity.getName().toLowerCase().contains(searchTerm);

          return m1;
        };

    setItems(messagingProvidersService.listAll(), filter);
  }

}
