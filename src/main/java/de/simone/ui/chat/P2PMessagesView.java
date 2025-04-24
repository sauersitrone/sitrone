package de.simone.ui.chat;

import java.time.*;
import java.util.*;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.messages.*;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.tabs.*;
import com.vaadin.flow.component.tabs.Tabs.*;
import com.vaadin.flow.dom.*;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import de.simone.*;
import de.simone.backend.*;
import de.simone.ui.components.*;
import io.quarkus.panache.common.*;
import jakarta.annotation.security.*;
import jakarta.inject.*;
import jakarta.transaction.*;

@RolesAllowed({ "Sitrone.master", "P2PMessagesView" })
@Route(value = "P2PMessagesView", layout = MainLayout.class)
public class P2PMessagesView extends HorizontalLayout implements BeforeEnterObserver, PropertyChangeListener {

    private ChatInfo currentChat;
    private Tabs tabs;
    private VerticalLayout chatContainer;
    private MessageInput messageInput;
    private Aside topicsBar;
    private TTimer timer;
    private User currentUser;

    protected MessageList messageList;

    @Inject
    protected P2PChatsService p2pChatsService;

    public P2PMessagesView() {
        addClassNames(Width.FULL, Display.FLEX, Flex.AUTO);
        setSpacing(false);
        timer = UIUtils.getTimer(this, 2);
        tabs = new Tabs();
        tabs.setOrientation(Orientation.VERTICAL);
        tabs.addClassNames(Flex.GROW, Flex.SHRINK, Overflow.HIDDEN);
        currentUser = SecurityUtils.getLoggedUser();
        messageInput = new MessageInput(e -> sendMessage(e.getValue()));

        messageList = new MessageList();
        messageList.setSizeFull();

        VoiceRecognition voiceRecognition = new VoiceRecognition();
        voiceRecognition.addResultListener(e -> sendMessage(e.getSpeech()));
        HorizontalLayout inputLayout = UIUtils.getCompactHorizontalLayout(true, messageInput, voiceRecognition);
        inputLayout.setFlexGrow(1, messageInput);

        // Layouting
        chatContainer = new VerticalLayout();
        chatContainer.addClassNames(Flex.AUTO, Overflow.HIDDEN);
        chatContainer.add(messageList, inputLayout);

        topicsBar = new Aside();
        topicsBar.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW_NONE, Flex.SHRINK_NONE,
                Background.CONTRAST_5);
        topicsBar.setWidth("18rem");

        topicsBar.add(tabs);

        add(timer, chatContainer, topicsBar);
        setSizeFull();

        // Change the topic id of the chat when a new tab is selected
        tabs.addSelectedChangeListener(e -> updateMessages());
    }

    @Transactional
    protected void updateMessages() {
        currentChat = ((ChatTab) tabs.getSelectedTab()).getChatInfo();
        currentChat.resetUnread();

        List<MessageListItem> items = new ArrayList<>();
        List<P2PMessage> chatMessages = p2pChatsService.getMessages(currentUser.id);
        for (P2PMessage message : chatMessages) {
            message.seenBy.add(currentUser.id);
            P2PMessage.getEntityManager().merge(message);

            LocalDateTime localDateTime = message.getCreatedAt();
            Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
            MessageListItem item = new MessageListItem(message.message, instant, message.senderName,
                    message.senderImage);

            items.add(item);
        }

        messageList.setItems(items);
    }

    public void setVisibleTopicsBar(boolean visible) {
        topicsBar.setVisible(visible);
    }

    public void setVisibleMessageInput(boolean visible) {
        messageInput.setVisible(visible);
    }

    private void buildChatMembers() {
        List<P2PGroup> groups = p2pChatsService.getChatGroups();
        for (P2PGroup group : groups) {

            // create tabs according to topics
            ChatInfo chat = new ChatInfo(group, group.getUnreadMessages(currentUser.id));
            ChatTab tab = new ChatTab(chat);
            tab.addClassNames(JustifyContent.BETWEEN);

            Span badge = new Span();
            chat.setUnreadBadge(badge);
            badge.getElement().getThemeList().add("badge small error");
            tab.add(new Span(group.name), badge);
            tabs.add(tab);
        }
    }

    public void updateUnread(ChatInfo chat) {
        if (currentChat != chat) {
            chat.incrementUnread();
        }
    }

    private void sendMessage(String message) {
        ChatTab chatTab = (ChatTab) tabs.getSelectedTab();
        currentChat = chatTab.getChatInfo();
        currentChat.resetUnread();
        sendMessage(currentChat.group, message, true);
    }

    /**
     * send a message
     * 
     * @param to       - the group
     * @param message  - the message
     * @param noRepeat - true will not send the message if the last message is equal
     *                 to this message
     */
    private void sendMessage(P2PGroup to, String message, boolean noRepeat) {
        P2PMessage messageOld = P2PMessage.find("id = ?1", Sort.descending("id"), to.id).firstResult();
        boolean send = true;
        if (noRepeat && messageOld != null)
            send = !messageOld.message.equals(message);

        if (!send)
            return;

        P2PMessage message2 = new P2PMessage();
        message2.senderId = currentUser.id;
        message2.senderImage = currentUser.avatar;
        message2.senderName = currentUser.getFullName();
        message2.message = message;
        message2.reciverId = to.id;

        p2pChatsService.save(message2);
        updateMessages();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        Page page = attachEvent.getUI().getPage();
        page.retrieveExtendedClientDetails(details -> setMobile(details.getWindowInnerWidth() < 740));
        page.addBrowserWindowResizeListener(e -> setMobile(e.getWidth() < 740));
    }

    private void setMobile(boolean mobile) {
        tabs.setOrientation(mobile ? Orientation.HORIZONTAL : Orientation.VERTICAL);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        timer.start();
        buildChatMembers();
        tabs.setSelectedIndex(0);
        updateMessages();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        List<P2PMessage> chatMessagesNew = p2pChatsService.getMessages(currentUser.id);
        ChatInfo chatInfo = ((ChatTab) tabs.getSelectedTab()).getChatInfo();
        if (chatInfo.messageCount != chatMessagesNew.size()) {
            updateMessages();
        }
    }
}
