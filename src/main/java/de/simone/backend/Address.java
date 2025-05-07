package de.simone.backend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvBindByName;

import de.simone.TranslationProvider;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

@Entity
@Table(name = "Addresses")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address extends TAEntity {

    public static final String HOME = "HOME";
    public static final String WORK = "WORK";
    public static final String MAIN = "MAIN";
    public static final String OTHER = "OTHER";

    public static final String DAME = "DAME";
    public static final String MISTER = "MISTER";
    public static final String WITHOUT = "WITHOUT";

    public static final String DOCTOR = "DOCTOR";
    public static final String PROFESOR = "PROFESOR";
    public static final String PROFDOC = "PROFDOC";
    public static final String NOTITLE = "NOTITLE";

    // formal/soft salutation prefix (constant group)
    public static final String SALUTATION_PREFIX = "salutation-";

    @NotNull
    @CsvBindByName
    @OneOfStrings({ HOME, WORK, MAIN, OTHER })
    public String type = MAIN;

    @CsvBindByName
    public boolean isMain;

    @NotNull
    @CsvBindByName
    @OneOfStrings({ WITHOUT, DAME, MISTER })
    public String salutation = WITHOUT;

    @NotNull
    @CsvBindByName
    @OneOfStrings({ DOCTOR, PROFESOR, PROFDOC, NOTITLE })
    public String title = NOTITLE;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String firstName;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String lastName;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_DESCRIPTIONS)
    public String street;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = 5)
    public String postcode;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String city;

    @NotNull
    @NotBlank
    @CsvBindByName
    @Size(max = SIZE_2)
    public String country = "DE";

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    public String email;

    @Size(max = SIZE_NAMES)
    public String phone;

    @Size(max = SIZE_255)
    public String picture;

    @Size(max = SIZE_255)
    public String signature;

    public Address() {
        //
    }

    /**
     * Qute interface
     * 
     * @return - the mime/image
     */
    @JsonIgnore
    public String getEmbeddedPicture() {
        return super.getImage(picture);
    }

    /**
     * Qute interface
     * 
     * @return - the mime/image
     */
    @JsonIgnore
    public String getEmbeddedSignature() {
        return super.getImage(signature);
    }

    public String getFullName() {
        return getFullName(firstName, lastName);
    }

    public static String getFullName(String firstName, String lastName) {
        String string = "";
        string += firstName == null ? "" : firstName;
        string += lastName == null ? "" : " " + lastName;
        return string;
    }

    public String getAddress() {
        return getAddress(country, street, postcode, city);
    }

    /**
     * retrun standar formated address (Shönestr 01099 Dresden, germany)
     * 
     * @return - standar post address
     */
    public static String getAddress(String country, String street, String postcode, String city) {
        String countryName = country == null ? "" : country;
        return street + ", " + postcode + " " + city + ", " + countryName;
    }

    /**
     * Qute interface
     * 
     * @return - the Location
     */
    public String getLocation() {
        return getLocation(country, city);
    }

    /**
     * Return social media location (city, Country)
     * 
     * @param country - the country
     * @param city    - the city
     * @return - the Location
     */
    public static String getLocation(String country, String city) {
        String countryName = country == null ? "" : country;
        return city + ", " + countryName;
    }

    /**
     * return the standar german post address (Frau Müller Maria Shönestr 01099
     * Dresden, germany)
     * 
     * @return - standar post address
     */
    public String getFullPostAddress() {
        String salu = getMailSalutation();
        return salu + " " + getAddress();
    }

    /**
     * return the standar german salutation prefix (Prof Dr. Herr Müller)
     * 
     * @return standar german salutation
     */
    public String getMailSalutation() {
        String tit = Address.NOTITLE.equals(title) ? " " : TranslationProvider.getString("titles", title);
        String salu = tit
                + (Address.WITHOUT.equals(salutation) ? " " : TranslationProvider.getString("salutations", salutation));
        return salu.trim() + " " + lastName + " " + firstName;
    }

    /**
     * @see Address#getGermanSalutation(String, String, String, String)
     */
    public String getGermanSalutation(String type) {
        return Address.getGermanSalutation(type, salutation, lastName, firstName);
    }

    /**
     * return a formal or soft german salutation. if the type argument is unkown,
     * return formal salutaion
     * 
     * <li>Formal: Sehr geehrter Herr Prof Dr. Herr Müller
     * <li>soft: Liebe Frau Anna
     * 
     * @param type       - formal, soft
     * @param salutation - dame, mister
     * @param lastName   - the last name
     * @param firstName  - the first name
     * @return the salutation text
     */
    public static String getGermanSalutation(String type, String salutation, String lastName, String firstName) {
        // this method is Qute interface. that is way dont habe static constant
        String type2 = "soft".equals(type) ? type : "formal";
        String saluPref = SALUTATION_PREFIX + salutation + "-" + type2;
        String sal = TranslationProvider.getTranslation(saluPref, lastName, firstName);
        return sal;
    }

}
