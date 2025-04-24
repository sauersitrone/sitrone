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
@PWA(name = "Sitrone", shortName = "Sitrone", offlineResources = {})
@NpmPackage(value = "@vaadin-component-factory/vcf-nav", version = "1.0.6")

public class Sitrone implements AppShellConfigurator {

    @ConfigProperty(name = "quarkus.hibernate-orm.jdbc.timezone")
    String jdbcTimezone;

    @ConfigProperty(name = "sitrone.timezone")
    String sitroneTimezone;

    @ConfigProperty(name = "sitrone.country")
    String country;

    @ConfigProperty(name = "sitrone.language")
    String language;

    @ConfigProperty(name = "sitrone.version")
    public String version;

    private static Sitrone sitrone;

    public static void main(String... args) {
        Quarkus.run(args);
    }

    public static Sitrone getInstance() {
        return sitrone;
    }

    
    void onStart(@Observes StartupEvent ev, Router router) throws Exception {
        sitrone = this; // NOSONAR
        TimeZone.setDefault(TimeZone.getTimeZone(sitroneTimezone));

        router.route()
                .method(HttpMethod.GET).method(HttpMethod.HEAD)
                .path("/media/*")
                .handler(StaticHandler.create("media/"));        
    }
}
