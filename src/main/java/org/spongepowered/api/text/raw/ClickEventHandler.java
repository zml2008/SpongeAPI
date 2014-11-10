package org.spongepowered.api.text.raw;

public interface ClickEventHandler {

    ClickAction getAction();

    String getValue();

    static enum ClickAction {

        OPEN_URL,
        RUN_COMMAND,
        CHANGE_PAGE,
        SUGGEST_COMMAND

    }

}
