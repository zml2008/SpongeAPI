package org.spongepowered.api.text.chat;

import java.util.List;

class NullChatPositionFactory implements ChatPositionFactory {

    @Override
    public ChatPosition parsePosition(String name) {
        return null;
    }

    @Override
    public List<ChatPosition> getPositions() {
        return null;
    }
}
