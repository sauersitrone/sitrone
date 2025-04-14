package de.simone;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.auth.ViewAccessChecker;

import de.simone.ui.login.ExternalLogin;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TServiceInitListener implements VaadinServiceInitListener {

    private ViewAccessChecker accessChecker;
    private static List<String> vaadinSessions = new ArrayList<>();

    public TServiceInitListener() {
        accessChecker = new ViewAccessChecker();
        accessChecker.setLoginView(ExternalLogin.class);
    }

    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        // i18n
        System.getProperties().setProperty("vaadin.i18n.provider", TranslationProvider.class.getName());

        // security: intercept attempts to enter all views.
        serviceInitEvent.getSource().addUIInitListener(
                uiInitEvent -> uiInitEvent.getUI().addBeforeEnterListener(accessChecker));

        // update the global session counter
        // serviceInitEvent.getSource().addSessionInitListener(this::storeSession);
        // serviceInitEvent.getSource().addSessionDestroyListener(this::removeSession);

        // disable default theme -> loading indicator isn't shown
        // serviceInitEvent.getSource().addUIInitListener(uiInitEvent -> {
        // LoadingIndicatorConfiguration conf =
        // uiInitEvent.getUI().getLoadingIndicatorConfiguration();
        // conf.setApplyDefaultTheme(false);
        // });
    }

}
