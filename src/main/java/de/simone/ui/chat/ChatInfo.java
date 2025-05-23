
package de.simone.ui.chat;
import com.vaadin.flow.component.html.Span;

import de.simone.backend.P2PGroup;

public class ChatInfo {

    public String name;
    public P2PGroup group;
    public int messageCount;

    private int unread;
    private Span unreadBadge;

    public ChatInfo(P2PGroup channel, int unread) {
        this.group = channel;
        this.name = channel.name;
        this.unread = unread;
    }

    public void resetUnread() {
        unread = 0;
        updateBadge();
    }

    public void incrementUnread() {
        unread++;
        updateBadge();
    }

    private void updateBadge() {
        unreadBadge.setText(unread + "");
        unreadBadge.setVisible(unread != 0);
    }

    public void setUnreadBadge(Span unreadBadge) {
        this.unreadBadge = unreadBadge;
        updateBadge();
    }
}
