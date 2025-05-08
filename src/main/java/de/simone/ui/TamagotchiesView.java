package de.simone.ui;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.*;

import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.Adult;
import de.simone.backend.MessagingProvidersService;
import de.simone.backend.Tamagotchi;
import de.simone.backend.TamagotchiesService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

@RolesAllowed({ "Sitrone.master", "Tamagotchies" })
@Route(value = "Tamagotchies", layout = MainLayout.class)
public class TamagotchiesView extends TAView<Tamagotchi> {

    @Inject
    TamagotchiesService tamagotchiesService;
    @Inject
    MessagingProvidersService messagingProvidersService;

    public TamagotchiesView() {
        this.grid = UIUtils.getGrid(Tamagotchi.class);

        // mobile
        grid.addColumn(
                new ComponentRenderer<>(
                        ge -> {
                            return new Span();

                            // MovilListItem mli = new MovilListItem(ge.isEnabled, ge.firstName + " " +
                            // ge.lastName,
                            // ge.email, null, null);
                            // mli.setAvatar(ge.firstName + " " + ge.lastName, ge.avatar);
                            // return mli;
                        }))
                .setHeader(getTranslation("Tamagotchi.name"))
                .setComparator(te -> te.name)
                .setSortable(true)
                .setAutoWidth(true);

        // desktop
        UIUtils.setIdColumn(grid);

        grid.addColumn(new ComponentRenderer<>(UIUtils::getTamagotchiRender))
                .setHeader(getTranslation("Tamagotchi.name"))
                .setComparator(te -> te.name)
                .setSortable(true)
                .setAutoWidth(true);

        grid.addColumn(new ComponentRenderer<>(te -> {
            Adult adult = Adult.find("tamagotchiId = ?1", te.id).firstResult();
            return UIUtils.getAdutRender(adult);
        }))
                .setHeader(getTranslation("Adult"))
                .setAutoWidth(true);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        init(Tamagotchi.class, TamagotchiForm.class, tamagotchiesService);
        SerializablePredicate<Tamagotchi> filter = entity -> {
            String searchTerm = searchField.getValue().trim().toLowerCase();
            if (searchTerm.isEmpty())
                return true;

            boolean m1 = entity.name.toLowerCase().contains(searchTerm);

            return m1;
        };

        setItems(tamagotchiesService.listAll(), filter);
    }

}
