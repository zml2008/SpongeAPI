package org.spongepowered.api.text.raw;

public interface HoverEventHandler {

    HoverAction getAction();

    String getValue();

    static enum HoverAction {

        SHOW_TEXT,
        SHOW_ITEM,
        SHOW_ACHIEVEMENT,
        SHOW_ENTITY

    }

}
