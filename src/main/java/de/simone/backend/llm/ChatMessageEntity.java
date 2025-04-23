package de.simone.backend.llm;

import de.simone.backend.*;
import dev.langchain4j.data.message.*;
import jakarta.persistence.*;

@Entity
@Table(name = "ChatMessageEntities", indexes = {
		@Index(name = "ChatMessageEntities_memoryId", columnList = "memoryId") })
public class ChatMessageEntity extends TAEntity {

	public Long memoryId;

	public ChatMessageType type;

	@Column(columnDefinition = "TEXT")
	public String message;

}
