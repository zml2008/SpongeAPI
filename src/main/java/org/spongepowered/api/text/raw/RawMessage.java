package org.spongepowered.api.text.raw;

public interface RawMessage {

    String getText();

    Iterable<RawMessage> getExtra();

    FormatCode getColor();

    boolean getBold();

    boolean getUnderlined();

    boolean getItalic();

    boolean getStrikethrough();

    boolean getObfuscated();

    boolean getInsertion();

    ClickAction getClickEventHandler();

    HoverEventHandler getHoverEventHandler();

    String getTranslationIdentifier();

    Score getScore();

}
