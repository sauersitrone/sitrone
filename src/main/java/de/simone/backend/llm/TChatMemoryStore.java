package de.simone.backend.llm;

import java.util.*;

import dev.langchain4j.data.message.*;
import dev.langchain4j.store.memory.chat.*;

/**
 * TODO: i dont know the frequency in wich this class in invoqued. to avoid
 * overread on db, implement first a copy of InMemoryChatMemoryStore, make trace
 * and the continue
 * 
 * check MessageWindowChatMemory
 */
public class TChatMemoryStore implements ChatMemoryStore {

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        List<ChatMessageEntity> entities = ChatMessageEntity.find("memoryId = 1?", memoryId).list();
        List<ChatMessage> messages = new ArrayList<>();
        for (ChatMessageEntity entity : entities) {
            if (entity.type == ChatMessageType.AI)
                messages.add(AiMessage.from(entity.message));
            if (entity.type == ChatMessageType.SYSTEM)
                messages.add(SystemMessage.from(entity.message));
            if (entity.type == ChatMessageType.USER)
                messages.add(UserMessage.from(entity.message));
        }

        return messages;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMessages'");
    }

    @Override
    public void deleteMessages(Object memoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMessages'");
    }

}
