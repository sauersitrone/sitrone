package de.simone.backend;

import java.util.ArrayList;
import java.util.List;

import de.simone.TranslationProvider;
import de.simone.UIUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ChatsService extends TAService<ChatMessage> {

    public ChatsService() {
        super(ChatMessage.class);
    }

    @Override
    public ChatMessage get(Long id) throws WebApplicationException {
        return getImpl(id);
    }

    public List<ChatMessage> getChatMessages(Long reciverId) {
        List<ChatMessage> list =   ChatMessage.list("reciverId = ?1 OR senderId = ?2", reciverId, reciverId);
        return list;
      }
    

    public List<ChatGroup> getChatGroups() {
        List<ChatGroup> groups = new ArrayList<>();
        List<Adult> adults = Adult.listAll();
        for (Adult adult : adults) {
            ChatGroup group = new ChatGroup();
            group.image = adult.avatar;
            group.name = adult.getFullName();
            group.id = adult.id;
            groups.add(group);
        } 
        return groups;
    }

    public void sendMessage(String messagekey, Long reciverId, Object... parms) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.senderId = -1L;
        chatMessage.senderImage = UIUtils.BOT_AVATAR;
        chatMessage.senderName = UIUtils.BOT_NAME;
        chatMessage.reciverId = reciverId;
        chatMessage.message = TranslationProvider.getTranslation(messagekey, parms);
        save(chatMessage);
    }

    @Override
    public Response delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Response duplicate(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'duplicate'");
    }

    @Override
    public Response save(ChatMessage entity) {
        return saveImpl(entity);
    }
}
