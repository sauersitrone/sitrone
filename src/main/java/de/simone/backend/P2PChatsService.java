package de.simone.backend;

import java.util.ArrayList;
import java.util.List;

import de.simone.TranslationProvider;
import de.simone.UIUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class P2PChatsService extends TAService<P2PMessage> {

    public P2PChatsService() {
        super(P2PMessage.class);
    }

    @Override
    public P2PMessage get(Long id) throws WebApplicationException {
        return getImpl(id);
    }

    public List<P2PMessage> getMessages(Long reciverId) {
        List<P2PMessage> list =   P2PMessage.list("reciverId = ?1 OR senderId = ?2", reciverId, reciverId);
        return list;
      }
    

    public List<P2PGroup> getChatGroups() {
        List<P2PGroup> groups = new ArrayList<>();
        List<Adult> adults = Adult.listAll();
        for (Adult adult : adults) {
            P2PGroup group = new P2PGroup();
            group.image = adult.avatar;
            group.name = adult.getFullName();
            group.id = adult.id;
            groups.add(group);
        } 
        return groups;
    }

    public void sendMessage(String messagekey, Long reciverId, Object... parms) {
        P2PMessage chatMessage = new P2PMessage();
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
    public Response save(P2PMessage entity) {
        return saveImpl(entity);
    }
}
