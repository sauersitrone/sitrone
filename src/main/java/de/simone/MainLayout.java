package de.simone;

import java.util.*;

import org.vaadin.lineawesome.*;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.applayout.*;
import com.vaadin.flow.component.avatar.*;
import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.contextmenu.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent.*;
import com.vaadin.flow.component.sidenav.*;
import com.vaadin.flow.dom.*;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.*;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import de.simone.backend.*;
import de.simone.components.*;
import de.simone.components.Layout;
import de.simone.components.dialogs.*;
import de.simone.ui.*;
import de.simone.ui.chat.*;
import de.simone.ui.components.*;
import de.simone.utilities.*;
import de.simone.views.components.*;
import de.simone.views.templates.*;
import de.simone.views.templates.wizard.*;
import jakarta.inject.*;

/** The main view is a top-level placeholder for other views. */
public class MainLayout extends AppLayout implements BeforeEnterObserver, PropertyChangeListener {

    private H1 viewTitle;
    private TTimer timer;
    private HorizontalLayout toolBar;
    private User currentUser;
    private List<MenuItem> languageItems;
    private Image sitroneLogo;

    @Inject
    UsersService usersService;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(FontSize.LARGE);

        this.toolBar = new HorizontalLayout();
        toolBar.setJustifyContentMode(JustifyContentMode.END);
        toolBar.setAlignItems(Alignment.CENTER);
        toolBar.addClassName(LumoUtility.Margin.Right.LARGE);

        HorizontalLayout horizontalLayout = new HorizontalLayout(toggle, viewTitle, toolBar);
        horizontalLayout.setWidthFull();
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout.setFlexGrow(1, viewTitle);
        horizontalLayout.getStyle().set("overflow", "auto");

        MessagesDialog messagesMenu = new MessagesDialog();

        Badge messageBadge = new Badge();
        messageBadge.addClassNames("end-xs", Position.ABSOLUTE, "top-xs");
        messageBadge.addThemeVariants(
                BadgeVariant.SUCCESS, BadgeVariant.PILL, BadgeVariant.PRIMARY, BadgeVariant.SMALL);

        Button messageButton = new Button(LineAwesomeIcon.COMMENTS.create(), e -> messagesMenu.showModal());
        messageButton.addClassNames(Margin.Start.AUTO);
        messageButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        messageButton.setAriaLabel("View messages (4)");
        messageButton.setSuffixComponent(messageBadge);
        messageButton.setTooltipText("View messages (4)");

        NotificationsDialog notificationsMenu = new NotificationsDialog();

        Badge notificationsBadge = new Badge();
        notificationsBadge.addClassNames("end-xs", Position.ABSOLUTE, "top-xs");
        notificationsBadge.addThemeVariants(
                BadgeVariant.ERROR, BadgeVariant.PILL, BadgeVariant.PRIMARY, BadgeVariant.SMALL);

        Button notificationsButton = new Button(LumoIcon.BELL.create(), e -> notificationsMenu.showModal());
        notificationsButton.addClassNames(Margin.Start.XSMALL);
        notificationsButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        notificationsButton.setAriaLabel("View notifications (2)");
        notificationsButton.setSuffixComponent(notificationsBadge);
        notificationsButton.setTooltipText("View notifications (2)");

        UserDialog userMenu = new UserDialog();

        Avatar avatar = new Avatar("John Smith");
        avatar.addClassNames(Margin.Horizontal.SMALL);
        avatar.getElement().addEventListener("click", e -> userMenu.showModal());
        avatar.setTooltipEnabled(true);

        this.timer = UIUtils.getTimer(this, 10);
        this.languageItems = new ArrayList<>();

        addToNavbar(
                true,
                toggle,
                timer,
                horizontalLayout,
                messageButton,
                messagesMenu,
                notificationsButton,
                notificationsMenu,
                avatar,
                userMenu);
    }

    private void addDrawerContent() {
        sitroneLogo = UIUtils.getAppLogo(Lumo.LIGHT);
        Span appName = new Span("Vaadin+");
        appName.addClassNames(FontSize.LARGE, FontWeight.SEMIBOLD);

        Layout nav = new Layout(createComponentNavigation(), createTemplatesNavigation());
        nav.setFlexDirection(Layout.FlexDirection.COLUMN);
        nav.setGap(Layout.Gap.MEDIUM);

        Scroller scroller = new Scroller(nav);

        addToDrawer(new Header(appName), scroller);
    }

    private SideNav createComponentNavigation() {
        SideNav nav = new SideNav("Components");
        nav.addItem(new SideNavItem(getTranslation("UsersView"), UsersView.class,
                LineAwesomeIcon.USER.create()));
        nav.addItem(new SideNavItem(getTranslation("AdultsView"), AdultsView.class,
                LineAwesomeIcon.ROBOT_SOLID.create()));
        nav.addItem(new SideNavItem(getTranslation("DrugsView"), DrugsView.class,
                LineAwesomeIcon.PILLS_SOLID.create()));
        nav.addItem(new SideNavItem(getTranslation("EventsView"), EventsView.class,
                LineAwesomeIcon.PLANE_ARRIVAL_SOLID.create()));
        nav.addItem(new SideNavItem(getTranslation("RelativesView"), "Relatives/199268075270145",
                LineAwesomeIcon.PEOPLE_CARRY_SOLID.create()));
        nav.addItem(new SideNavItem(getTranslation("MessaginProvidersView"), MessaginProvidersView.class,
                LineAwesomeIcon.PAPER_PLANE.create()));
        nav.addItem(new SideNavItem(getTranslation("P2PMessagesView"), P2PMessagesView.class,
                LineAwesomeIcon.COMMENTS.create()));
        nav.addItem(new SideNavItem(getTranslation("TamagotchiesView"), TamagotchiesView.class,
                LineAwesomeIcon.DOG_SOLID.create()));
        nav.addItem(new SideNavItem(getTranslation("TamagotchiChat"), TamagotchiChat.class,
                LineAwesomeIcon.CAT_SOLID.create()));
        nav.addItem(new SideNavItem(getTranslation("PrescriptionsView"), "Prescriptions/199268075270145",
                LineAwesomeIcon.PILLS_SOLID.create()));
        nav.addItem(
                new SideNavItem("App bars", AppBarsView.class, LineAwesomeIcon.BARS_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Breadcrumbs", BreadcrumbsView.class,
                        LineAwesomeIcon.BREAD_SLICE_SOLID.create()));
        nav.addItem(
                new SideNavItem("Checkboxes", CheckboxesView.class,
                        LineAwesomeIcon.CHECK_SQUARE.create()));
        nav.addItem(new SideNavItem("Dialogs", DialogsView.class, LineAwesomeIcon.WINDOWS.create()));
        nav.addItem(
                new SideNavItem("Empty states", EmptyStatesView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new SideNavItem("Grids", GridsView.class, LineAwesomeIcon.LIST_SOLID.create()));
        nav.addItem(
                new SideNavItem("Headers", HeadersView.class, LineAwesomeIcon.HEADING_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Highlights", HighlightsView.class,
                        LineAwesomeIcon.CHART_LINE_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Input groups", InputGroupsView.class,
                        LineAwesomeIcon.TERMINAL_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Key-value pairs", KeyValuePairsView.class,
                        LineAwesomeIcon.KEY_SOLID.create()));
        nav.addItem(new SideNavItem("Lists", ListsView.class, LineAwesomeIcon.LIST_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Menu bars", MenuBarsView.class,
                        LineAwesomeIcon.ELLIPSIS_V_SOLID.create()));
        nav.addItem(
                new SideNavItem("Navigation rail", NavRailView.class,
                        LineAwesomeIcon.BARS_SOLID.create()));
        nav.addItem(
                new SideNavItem("Notifications", NotificationsView.class,
                        LineAwesomeIcon.BELL.create()));
        nav.addItem(
                new SideNavItem(
                        "Radio buttons", RadioButtonsView.class,
                        LineAwesomeIcon.CHECK_CIRCLE_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Search dialogs", SearchDialogsView.class,
                        LineAwesomeIcon.SEARCH_SOLID.create()));
        nav.addItem(
                new SideNavItem("Sidebar", SidebarsView.class, LineAwesomeIcon.COLUMNS_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Statuses", StatusesView.class,
                        LineAwesomeIcon.INFO_CIRCLE_SOLID.create()));
        nav.addItem(
                new SideNavItem("Steppers", SteppersView.class,
                        LineAwesomeIcon.WALKING_SOLID.create()));
        nav.addItem(
                new SideNavItem("Tabs", TabsView.class, LineAwesomeIcon.EXCHANGE_ALT_SOLID.create()));
        nav.addItem(new SideNavItem("Top nav", TopNavView.class, LineAwesomeIcon.BARS_SOLID.create()));
        return nav;
    }

    private SideNav createTemplatesNavigation() {
        SideNav nav = new SideNav("Templates");
        nav.addItem(
                new SideNavItem("Checkout", CheckoutView.class, LineAwesomeIcon.CREDIT_CARD.create()));
        nav.addItem(
                new SideNavItem("Customers", CustomersView.class, LineAwesomeIcon.HANDSHAKE.create()));
        nav.addItem(
                new SideNavItem(
                        "Dashboard º1", DashboardView1.class,
                        LineAwesomeIcon.CHART_LINE_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Dashboard º2", DashboardView2.class,
                        LineAwesomeIcon.CHART_LINE_SOLID.create()));
        nav.addItem(new SideNavItem("Files", FilesView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new SideNavItem("Hotels", HotelsView.class, LineAwesomeIcon.HOTEL_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Product details",
                        ProductDetailsView.class,
                        LineAwesomeIcon.INFO_CIRCLE_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Product list", ProductListView.class,
                        LineAwesomeIcon.TH_LARGE_SOLID.create()));
        nav.addItem(new SideNavItem("Profile", ProfileView.class, LineAwesomeIcon.USER.create()));
        nav.addItem(
                new SideNavItem(
                        "Shopping cart", ShoppingCartView.class,
                        LineAwesomeIcon.SHOPPING_CART_SOLID.create()));
        nav.addItem(
                new SideNavItem(
                        "Validation form", ValidationView.class,
                        LineAwesomeIcon.CHECK_CIRCLE.create()));
        nav.addItem(
                new SideNavItem(
                        "Wizard (WIP)", Step1View.class,
                        LineAwesomeIcon.HAT_WIZARD_SOLID.create()));
        return nav;
    }

    private String getCurrentPageTitle() {
        String pageTitle = "";
        // first: try HasDynamicTitle (useful with detail pages)
        Component content = getContent();
        if (content instanceof HasDynamicTitle)
            return ((HasDynamicTitle) content).getPageTitle();

        PageTitle title = getContent().getClass().getSuperclass().getAnnotation(PageTitle.class);
        if (title != null && TranslationProvider.isValidTranslation(title.value())) {
            pageTitle = getTranslation(title.value());
        }

        // second: try i18n with the class name (useful for list views)
        String clsName = UIUtils.getSimpleClassName(getContent());
        if (pageTitle.isEmpty() && TranslationProvider.isValidTranslation(clsName)) {
            pageTitle = getTranslation(clsName);
        }

        return pageTitle;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());

        // move the global toolbar
        toolBar.removeAll();
        if (getContent() instanceof TAView) {
            TAView<?> gView = (TAView<?>) getContent();
            toolBar.add(gView.getGlobalToolBar());
        }
        if (getContent() instanceof TAForm) {
            TAForm<?> form = (TAForm<?>) getContent();
            toolBar.add(form.getFooterComponents());
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // UI.getCurrent().getPage().addJavaScript("/webauthn.js", LoadMode.LAZY);
        setId(getClass().getName());
        currentUser = SecurityUtils.getLoggedUser();
        changeLanguage(currentUser.preferredLanguage);
        changeTheme(currentUser.preferredTheme);
        timer.start();
    }

    private void changeTheme(String newTheme) {
        String logoName = "goodfunds-logo-" + newTheme;
        sitroneLogo.setSrc(UIUtils.ICON_PATH + logoName + ".svg");
        currentUser.preferredTheme = newTheme;
        ThemeList themeList = UI.getCurrent().getElement().getThemeList();
        themeList.clear();
        if (newTheme.equals(Lumo.DARK))
            themeList.add(Lumo.DARK);
        if (newTheme.equals(Lumo.LIGHT))
            themeList.add(Lumo.LIGHT);
        usersService.save(currentUser, false);
    }

    private void changeLanguage(String newLanguage) {
        currentUser.preferredLanguage = newLanguage;
        languageItems.forEach(
                mi -> {
                    String lang = mi.getId().orElse("0");
                    mi.setChecked(lang.equals(newLanguage));
                });

        usersService.save(currentUser, false);
        UI.getCurrent().getSession().setLocale(new Locale(newLanguage));
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        // checkNewMessages();
    }
}
