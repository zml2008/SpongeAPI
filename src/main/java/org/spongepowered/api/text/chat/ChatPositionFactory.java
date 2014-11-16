package org.spongepowered.api.text.chat;

import java.util.List;

interface ChatPositionFactory {

    ChatPosition parsePosition(String name);

    List<ChatPosition> getPositions();
}
