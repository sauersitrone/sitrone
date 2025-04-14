package de.simone.backend;

import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "GlobalProperties")
public class GlobalProperty extends TAEntity {

    @NotNull
    @Min(value = 4)
    public Integer minPasswortLength = 4;

    @NotNull
    @Min(value = 3)
    public Integer maxFailAttempts = 3;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String externalTOTPLifeSpan = "PT2M";

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String defaultPassword = "gFundsRocks!"; // NOSONAR this is not a password. this is a sugestion for a
                                                    // password
    /**
     * JWT token default exp timestamp
     */
    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String accessTokeLifeSpan = "P60D";

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String magicLinkLifeSpan = "PT12H";

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String loginActionTimeout = "PT5M";

    @NotNull
    @OneToOne
    public Mailing confirmationCodeEmail;

    @NotNull
    @OneToOne
    public Mailing inviteUserMail;

    @NotNull
    @OneToOne
    public Mailing resetCredentialsMail; // not implemented

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_255)
    public String deeplBaseUrl;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_255)
    public String deeplApiKey;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_255)
    public String exchangeRateUrl;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_255)
    public String exchangeRateApiKey;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String appName;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_255)
    public String appEmail;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_255)
    public String appLogo;

    public boolean enableUserRegistration = true;
    public boolean forgotPassword = true;

    @Transient
    public String url;

    public static String getZitroneHost() {
        String host = "";
        VaadinServletRequest request = (VaadinServletRequest) VaadinService.getCurrentRequest();
        // background can use this class too. background jobs dont have uri
        if (request != null) {
            host = request.getRequestURL().toString();
            host = host.substring(4);
            // temporal solution to resolve protocol
            String scheme = host.contains("localhost") ? "http" : "https";
            host = scheme + host;

        }

        return host;
    }

    public static GlobalProperty getInstance() {
        GlobalProperty globalProperty = (GlobalProperty) GlobalProperty.listAll().get(0);
        return globalProperty;
    }

    public static String getUrl(String sufix) {
        return GlobalProperty.getZitroneHost() + sufix;
    }
}
