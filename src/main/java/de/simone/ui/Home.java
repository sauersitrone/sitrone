package de.simone.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.PropertyChangeEvent;
import com.vaadin.flow.dom.PropertyChangeListener;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.simone.MainLayout;
import de.simone.SecurityUtils;
import de.simone.UIUtils;
import de.simone.backend.HanniTask;
import de.simone.backend.ChatsService;
import de.simone.backend.UsersService;
import de.simone.ui.components.TTimer;
import de.simone.ui.components.HanniResourceCenter;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@PermitAll
@Route(value = "Homex", layout = MainLayout.class)
public class Home extends VerticalLayout implements HasDynamicTitle, BeforeEnterObserver, PropertyChangeListener {

    private HomeDashboard dashboard; 
    private TTimer timer;
    private HanniResourceCenter hanniResourceCenter;

        @Inject
    public ChatsService notificationsService;

    private static String hanniHeight = "670px";

    public Home() {
        setSizeFull();
        addClassName(LumoUtility.Background.CONTRAST_5);
        dashboard = new HomeDashboard();
        dashboard.addClassName(LumoUtility.MaxHeight.FULL);
        timer = UIUtils.getTimer(this, 1);
        hanniResourceCenter = new HanniResourceCenter();
        hanniResourceCenter.setHeight(hanniHeight);
        add(timer, dashboard);
    }

    @Override
    public String getPageTitle() {
        String clsName = UIUtils.getSimpleClassName(this);
        String transl = getTranslation(clsName);
        transl += " - " + SecurityUtils.getLoggedUser().userName;
        return transl;
    }

    @Override
    @Transactional
    public void beforeEnter(BeforeEnterEvent event) {

        // resource center
        hanniResourceCenter.init(this);
        timer.start();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (!SecurityUtils.getLoggedUser().isTaskCompleted(HanniTask.ONBOARDING))
            startOnboarding();
        timer.pause();
    }

    public void startOnboarding() {
        UsersService.setTaskCompleted(HanniTask.ONBOARDING);
        // GOnboarding onboarding = UIUtils.getOnboarding(HanniTask.ONBOARDING, this);
        // onboarding.start();
    }
}
