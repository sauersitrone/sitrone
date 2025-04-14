package de.simone;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinResponse;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinSession;

import de.simone.backend.User;
import io.quarkus.logging.Log;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

public class SecurityUtils {

    public static final String GF_RELOGGIN = "GF_RELOGGIN";
    private static final Map<Long, User> loggedUsers = new HashMap<>();
    private static final Map<Long, String> userP = new HashMap<>();

    private SecurityUtils() {

    }

    public static String[] getUserData(Long id) {
        String[] userdata = new String[2];

        // in dev enviroment, could be null
        if (loggedUsers.get(id) != null) {
            userdata[0] = loggedUsers.get(id).userName;
            userdata[1] = userP.get(id);
        }

        return userdata;
    }

    public static boolean isAdmin() {
        User user = getLoggedUser();
        if (user == null)
            return false;
        return user.isAdmin();
    }

    public static boolean isAuthenticated() {
        VaadinRequest request = VaadinService.getCurrentRequest();
        return request != null && request.getUserPrincipal() != null;
    }

    public static String getPrincipal() {
        VaadinRequest request = VaadinService.getCurrentRequest();
        if (request != null && request.getUserPrincipal() != null) {
            return request.getUserPrincipal().getName();
        }
        return null;
    }

    public static boolean hasAccess(String endPoint) {
        User user = getLoggedUser();
        if (user == null)
            return false;

        boolean ok = user.getEndPoints().contains(endPoint) || user.isAdmin();
        return ok;
    }

    public static void addCookie(String name, String value) {
        try {
            VaadinResponse response = VaadinService.getCurrentResponse();
            Cookie k = new Cookie(name, value);
            k.setHttpOnly(false);
            k.setMaxAge(10);
            response.addCookie(k);
        } catch (Exception e) {
            Log.error("", e);
        }
    }

    public static String getCookieValue(String name) {
        VaadinRequest request = VaadinService.getCurrentRequest();
        List<Cookie> cookies = request.getCookies() == null ? new ArrayList<>()
                : Arrays.asList(request.getCookies());
        Optional<Cookie> optional = cookies.stream().filter(c -> c.getName().equals(name)).findFirst();
        if (optional.isPresent())
            return optional.get().getValue();
        else
            return null;
    }

    public static User getLoggedUser() {
        VaadinRequest request = VaadinService.getCurrentRequest();
        if (request != null && request.getUserPrincipal() != null) {
            String userName = request.getUserPrincipal().getName();
            User user = User.find("userName = ?1", userName).firstResult();
            return user;
        }
        return null;
    }

    @Transactional
    public static boolean authenticate(String userName, String password) {
        VaadinServletRequest request = VaadinServletRequest.getCurrent();
        if (request == null) {
            // This is in a background thread and we can't access the request to
            // log in the user
            return false;
        }
        try {
            if (!isAuthenticated()) {
                request.login(userName, password);
            }
            User user = User.find("userName = ?1", userName).firstResult();
            user.lastSignIn = LocalDateTime.now();
            loggedUsers.put(user.id, user);
            userP.put(user.id, password);
            User.getEntityManager().merge(user);
        } catch (ServletException e) {
            e.printStackTrace();
            // TODO: test passkey. if the user use passkey, the user is already loged in
            if (!e.getMessage().contains("UT010030: User already logged in"))
                return false;
        }
        return true;
    }

    public static void logout() {
        VaadinSession.getCurrent().getSession().invalidate();
        // UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
    }

    private static String getRequestContextPath(HttpServletRequest request) {
        final String contextPath = request.getContextPath();
        return "".equals(contextPath) ? "/" : contextPath;
    }

}
