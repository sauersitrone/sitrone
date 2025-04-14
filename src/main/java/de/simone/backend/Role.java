
package de.simone.backend;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

@Entity
@Table(name = "Roles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role extends TAEntity {

    public static final String SELECTED = "SELECTED";
    public static final String NOT_SELECTED = "NOT_SELECTED";

    public static final String USER = "User";
    public static final String MASTER = "Master";

    @NotBlank
    @NotEmpty
    @Size(max = 20)
    public String roleName;

    @NotBlank
    @NotEmpty
    public String description;

    @NotNull
    @OneOfStrings({ SELECTED, NOT_SELECTED })
    public String selectionMethod = SELECTED;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<String> endPoints = new HashSet<>();

    public boolean isTemplate;

    public boolean isSpetialRole() {
        return MASTER.equals(roleName) || USER.equals(roleName);
    }
}
