package org.spongepowered.api.text.raw;

import org.spongepowered.api.text.raw.action.ClickAction;
import org.spongepowered.api.text.raw.action.HoverAction;

public interface RawMessage {

    String getText();

    Iterable<RawMessage> getExtra();

    FormatCode getColor();

    boolean getBold();

    boolean getUnderlined();

    boolean getItalic();

    boolean getStrikethrough();

    boolean getObfuscated();

    String getInsertion();

    ClickAction getClickAction();

    HoverAction getHoverAction();

    String getTranslationIdentifier();

    Score getScore();

}
