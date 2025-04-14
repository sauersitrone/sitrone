package de.simone.backend;

import java.util.Map;
import java.util.TreeMap;

import com.opencsv.bean.CsvBindByName;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;

import de.simone.SecurityUtils;
import io.vertx.core.json.Json;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "AuditLogs")
public class AuditLog extends TAEntity {

    public static final String INFO = "INFO";
    public static final String WARN = "WARN";
    public static final String ERROR = "ERROR";

    public static final String INSERT = "INSERT";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";
    public static final String LOG_IN = "LOG_IN";
    public static final String PASSWORD_RESET = "PASSWORD_RESET";

    // fundraisingbox log
    // Transaktionen exportiert
    // Transaktion gelöscht (via Listenoperation)
    // IP-Wechsel

    @CsvBindByName
    @Size(max = SIZE_255)
    public String userName;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String action;

    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String entity;

    @CsvBindByName
    public Long entityId;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String level;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Column(columnDefinition = "TEXT")
    public String details;

    public AuditLog() {
        //
    }

    public AuditLog(boolean b) {
        User user = SecurityUtils.getLoggedUser();

        // prefill the field if apply
        if (user != null) {
            userName = user.userName + ": " + user.getFullName() + " (" + user.email + ")";
        }
    }

    @Transactional
    public static void logInsert(TAEntity entity) {
        log(INSERT, INFO, entity);
    }

    @Transactional
    public static void logDelete(TAEntity entity) {
        log(DELETE, INFO, entity);
    }

    @Transactional
    public static void logUpdate(TAEntity entity) {
        log(UPDATE, INFO, entity);
    }

    @Transactional
    static void log(String action, String level, TAEntity entity) {
        // AuditLog log = new AuditLog(true);
        // log.level = level;
        // log.action = action;

        // // no entity related log
        // if (entity != null) {
        //     log.entity = entity.getClass().getSimpleName();
        //     log.entityId = entity.id;
        //     String det = Json.encodePrettily(entity);
        //     log.details = det;
        // }
        // log.persist();
    }

    @Transactional
    public static void logLoggin() {
        // WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        // AuditLog log = new AuditLog(true);
        // log.level = INFO;
        // log.action = LOG_IN;
        // String det = Json.encodePrettily(browser);
        // log.details = det;
        // log.persist();
    }

    @Transactional
    public static void logResetPassword(User target) {
        AuditLog log = new AuditLog(true);
        log.level = INFO;
        log.action = PASSWORD_RESET;
        log.entityId = target.id;
        Map<String, Object> map = new TreeMap<>();
        map.put("isTemporalPass", target.isTemporalPass);
        String det = Json.encodePrettily(map);
        log.details = det;
        log.persist();
    }
}
