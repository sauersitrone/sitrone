package de.simone.backend;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import net.andreinc.jbvext.annotations.misc.*;

@Entity
@Table(name = "ChatLogs")
public class ChatLog extends TAEntity {

    public static final String ADULT = "ADULT"; 
    public static final String TAMAGOTCHI = "TAMAGOTCHI";

    @NotNull
    public Long adultId;

    @NotNull
    @OneOfStrings({ ADULT, TAMAGOTCHI })
    public String sender = ADULT;
    
    @NotBlank
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    public String message;
}
