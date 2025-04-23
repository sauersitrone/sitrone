package de.simone.backend;

import java.time.*;
import java.util.*;

import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.*;
import com.opencsv.bean.*;

import de.simone.*;
import io.quarkus.hibernate.orm.panache.*;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class TAEntity extends PanacheEntityBase implements Comparable<TAEntity> {

    // standar size for fields
    public static final int SIZE_NAMES = 20;
    public static final int SIZE_DESCRIPTIONS = 60;
    public static final int SIZE_2 = 2;
    public static final int SIZE_3 = 3;
    public static final int SIZE_255 = 255;
    public static final int SIZE_TEXT = 1024;
    public static final int SIZE_TEXT_2K = 1024 * 2;
    public static final int SIZE_TEXT_4K = 1024 * 4;
    public static final int SIZE_TEXT_6K = 1024 * 6;

    public static final String CSV_DATE_FORMAT = "yyyy-MM-dd";
    public static final String CSV_DATETIME_FORMAT = "uuuu-MM-dd'T'HH:mm:ss";

    // source of this entity
    public static final String SOURCE_CRUD = "CRUD";
    public static final String SOURCE_IMPORT = "IMPORT";
    public static final String SOURCE_CONTRIBUTION = "CONTRIBUTION";
    public static final String SOURCE_WIDGET = "WIDGET";

    public static final String VALUE_NOT_SPECIFIED = "?";

    /**
     * this group of languages muss be constistent with
     * {@link TranslationProvider#getProvidedLocales()}
     */
    public static final String EN = "en";
    public static final String DE = "de";
    protected static final List<String> LAGUAGES = Arrays.asList(EN, DE);

    @Id
    @CsvBindByName
    @GeneratedValue(generator = "TGenerator")
    @GenericGenerator(name = "TGenerator", strategy = "de.simone.backend.TEntitiyIdentifierGenerator")
    public Long id;

    @JsonIgnore
    @CsvBindByName
    @CsvDate(TAEntity.CSV_DATETIME_FORMAT)
    private LocalDateTime createdAt;

    @JsonIgnore
    @CsvBindByName
    @CsvDate(TAEntity.CSV_DATETIME_FORMAT)
    private LocalDateTime updatedAt;

    private Long ownerId;

    @PrePersist
    protected void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setOwner(TAEntity owner) {
        if (owner == null || owner.isNewEntity())
            throw new IllegalArgumentException("Parameter owner or owner.id can.t be null");
        this.ownerId = owner.id;
    }

    @JsonIgnore
    public Long getOwner() {
        return ownerId;
    }

    /**
     * return the image.
     * <li>if the image source is internal, this method return the file content in
     * MIME format.
     * <li>if the souce is externa, the method return the value of the field (the
     * source)
     * 
     * @param imageField - the filed name who contain the image.
     * @return image
     */
    protected String getImage(String imageField) {
        // if the image is not set
        if (imageField == null)
            return "";
        // in mock date, return only the reference, else, return the embedded logo
        if (imageField.toLowerCase().startsWith("http"))
            return imageField;
        else
            return MiscellaneousService.readMimeDataFromFile(imageField);
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return id == null ? "0" : id.toString();
    }

    @Override
    public int hashCode() {
        if (id != null)
            return id.hashCode();
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj != null && obj.getClass().equals(this.getClass()))) {
            return false; // null or other class
        }
        TAEntity other = (TAEntity) obj;
        if (id != null) {
            return id.equals(other.id);
        }
        return false;
    }

    @Override
    public int compareTo(TAEntity o) {
        return Long.compare(id, o.id);
    }

    public boolean isNewEntity() {
        return id == null;
    }

    protected static Map<String, String> getMap(String source) {
        Map<String, String> map = new HashMap<>();
        String source2 = source;
        source2 = source2.substring(1, source2.length() - 1);
        String[] parts = source2.split(",");
        for (int i = 0; i < parts.length; i = i + 2) {
            map.put(parts[i], parts[i + 1]);
        }
        return map;
    }
}
