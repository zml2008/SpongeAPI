package org.spongepowered.api.text.raw;

import org.spongepowered.api.text.action.ClickAction;
import org.spongepowered.api.text.action.HoverAction;

import java.util.List;

public interface RawMessage {

    String getText();

    List<RawMessage> getExtra();

    TextFormat getColor();

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
