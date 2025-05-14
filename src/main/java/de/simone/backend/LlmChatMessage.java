package de.simone.backend;

import dev.langchain4j.data.message.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "LlmChatMessages")
public class LlmChatMessage extends TAEntity {

    @NotBlank
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    public String text;

    @Enumerated(EnumType.STRING)
    public ChatMessageType messageType;
    public boolean important;

    public LlmChatMessage() {
        //
    }

    public LlmChatMessage(ChatMessage chatMessage) {
        this.text = chatMessage.text();
        this.messageType = chatMessage.type();
        this.setSecondaryKey();
    }
}
