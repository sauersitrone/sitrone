package de.simone.backend;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.CsvBindByName;

import de.simone.SecurityUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Countries", indexes = {
        @Index(name = "Countries_code", columnList = "code") })
public class Country extends TAEntity {

    // default gfunds country selection
    public static final String EUROPE = "Europe";

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_2)
    public String code;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_255)
    public String region;

    public static String getName(String code) {
        if (StringUtils.isBlank(code))
            return "";
        Locale inLocale = Locale.US;
        if (SecurityUtils.getLoggedUser() != null && SecurityUtils.getLoggedUser().preferredLanguage.equals("de"))
            inLocale = Locale.GERMANY;
        Locale locale2 = new Locale("", code);
        return locale2.getDisplayCountry(inLocale);
    }

    public String getName() {
        if (code == null)
            return "";
        return getName(code);
    }
}
