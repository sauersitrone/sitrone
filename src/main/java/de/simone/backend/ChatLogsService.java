package de.simone.backend;

import de.simone.backend.llm.*;
import jakarta.enterprise.context.*;
import jakarta.inject.*;
import jakarta.transaction.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@ApplicationScoped
public class ChatLogsService extends TAService<ChatLog> {

    @Inject
    AiService aiService;

    public ChatLogsService() {
        super(ChatLog.class);
    }

    @Override
    public ChatLog get(Long id) throws WebApplicationException {
        return getImpl(id);
    }

    @Transactional
    public String sendMessage(Adult adult, Tamagotchi tamagotchi, String message) {
        ChatLog log = new ChatLog();
        log.setSecondaryKey();
        log.message = message;
        log.sender = ChatLog.ADULT;
        ChatLog.getEntityManager().persist(log);

        String anwer = aiService.chat(adult, tamagotchi, message);
        ChatLog log2 = new ChatLog();
        log2.setSecondaryKey();
        log2.message = anwer;
        log2.sender = ChatLog.TAMAGOTCHI;
        ChatLog.getEntityManager().persist(log2);

        return anwer;
    }

    @Override
    public Response save(ChatLog entity) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Response delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Response duplicate(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'duplicate'");
    }

}
