package org.spongepowered.api.text.raw;

import org.spongepowered.api.text.action.ClickAction;
import org.spongepowered.api.text.action.HoverAction;

import java.util.List;

public interface RawMessageBuilder {

    RawMessage build();

    RawMessageBuilder setText(String text);

    RawMessageBuilder addExtra(RawMessage extra);

    RawMessageBuilder addExtra(Iterable<RawMessage> extra);

    RawMessageBuilder addExtra(RawMessage... extra);

    RawMessageBuilder setColor(TextColor color);

    RawMessageBuilder setBold(boolean bold);

    RawMessageBuilder setUnderlined(boolean underlined);

    RawMessageBuilder setItalic(boolean italic);

    RawMessageBuilder setStrikethrough(boolean strikethrough);

    RawMessageBuilder setObfuscated(boolean obfuscated);

    RawMessageBuilder setInsertion(String insertion);

    RawMessageBuilder setClickAction(ClickAction clickAction);

    RawMessageBuilder setHoverAction(HoverAction hoverAction);

    RawMessageBuilder setTranslationIdentifier(String translationIdentifier);

    RawMessageBuilder setScore(Score score);

}
