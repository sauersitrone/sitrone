
package de.simone.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

@Entity
@Table(name = "UserPreferences")
public class UserPreference extends TAEntity {

    public static final String GRID = "GRID";
    public static final String LIST = "LIST";

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    public String className;

    @Size(max = SIZE_NAMES)
    @OneOfStrings({ GRID, LIST })
    public String view = LIST;

}