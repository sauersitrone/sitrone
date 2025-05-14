package de.simone.backend.llm;

import java.util.*;

import de.simone.backend.*;
import dev.langchain4j.data.message.*;
import dev.langchain4j.store.memory.chat.*;
import jakarta.enterprise.context.*;
import jakarta.transaction.*;

@ApplicationScoped
public class DBChatMemoryStore implements ChatMemoryStore {

    private Map<Long, List<String>> signatures = new HashMap<>();

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        Long sKey = Long.parseLong(memoryId.toString());
        List<LlmChatMessage> chatMessages = LlmChatMessage.list("secondaryKey = ?1", sKey);
        List<ChatMessage> chatMessages2 = new ArrayList<>();
        for (LlmChatMessage LlmChatMessage : chatMessages) {
            ChatMessage message = null;
            message = LlmChatMessage.messageType == ChatMessageType.AI ? AiMessage.from(LlmChatMessage.text) : message;
            // message = LlmChatMessage.messageType == ChatMessageType.CUSTOM ?
            // CustomMessage.from(LlmChatMessage.text) : message;
            message = LlmChatMessage.messageType == ChatMessageType.SYSTEM ? SystemMessage.from(LlmChatMessage.text)
            : message;
            // message = LlmChatMessage.messageType == ChatMessageType.TOOL_EXECUTION_RESULT
            // ? ToolExecutionResultMessage.from(LlmChatMessage.text) : message;
            message = LlmChatMessage.messageType == ChatMessageType.USER ? UserMessage.from(LlmChatMessage.text)
            : message;
            
            if (message == null)
            throw new NullPointerException("TChatMemoryProvidergetMessages(...) method found anull message.");
            
            chatMessages2.add(message);
        }
        return chatMessages2;
    }

    @Override
    @Transactional
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        System.out.println("DBChatMemoryStore.updateMessages()");
        Long sKey = Long.parseLong(memoryId.toString());
        LlmChatMessage.delete("secondaryKey = ?1", sKey);

        for (ChatMessage chatMessage : messages) {
            LlmChatMessage message = new LlmChatMessage(chatMessage);
            message.persist();
        }
    }

    @Override
    public void deleteMessages(Object memoryId) {
        // dont clear the table
    }

}
