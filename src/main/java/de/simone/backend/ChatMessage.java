package de.simone.backend;

import java.util.HashSet;
import java.util.Set;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ChatMessages")
public class ChatMessage extends TAEntity {

    @ElementCollection
    public Set<Long> seenBy = new HashSet<>(); 

    @NotNull
    public Long senderId;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    public String senderName;

    // @NotBlank
    // @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    public String senderImage;
    
    @NotNull
    public Long reciverId;

    @NotBlank
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    public String message;
}
