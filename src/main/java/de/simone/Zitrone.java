package de.simone;

import java.util.TimeZone;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import jakarta.enterprise.event.Observes;

@Theme("vaadin+")
@PWA(name = "Zitrone", shortName = "Zitrone", offlineResources = {})
@NpmPackage(value = "@vaadin-component-factory/vcf-nav", version = "1.0.6")

public class Zitrone implements AppShellConfigurator {

    @ConfigProperty(name = "quarkus.hibernate-orm.jdbc.timezone")
    String jdbcTimezone;

    @ConfigProperty(name = "zitrone.timezone")
    String zitroneTimezone;

    @ConfigProperty(name = "zitrone.country")
    String country;

    @ConfigProperty(name = "zitrone.language")
    String language;

    @ConfigProperty(name = "zitrone.version")
    public String version;

    private static Zitrone zitrone;

    public static void main(String... args) {
        Quarkus.run(args);
    }

    public static Zitrone getInstance() {
        return zitrone;
    }

    
    void onStart(@Observes StartupEvent ev, Router router) throws Exception {
        zitrone = this; // NOSONAR
        TimeZone.setDefault(TimeZone.getTimeZone(zitroneTimezone));

        router.route()
                .method(HttpMethod.GET).method(HttpMethod.HEAD)
                .path("/media/*")
                .handler(StaticHandler.create("media/"));        
    }
}
