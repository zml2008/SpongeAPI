/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered.org <http://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.chat;

import org.spongepowered.api.chat.action.ClickAction;
import org.spongepowered.api.chat.action.HoverAction;
import org.spongepowered.api.chat.style.TextStyle;

public interface RawMessageBuilder {

    RawMessage build();

    RawMessageBuilder setText(String text);

    RawMessageBuilder addExtra(RawMessage extra);

    RawMessageBuilder addExtra(Iterable<RawMessage> extra);

    RawMessageBuilder addExtra(RawMessage... extra);

    RawMessageBuilder setFormat(TextStyle format);

    RawMessageBuilder setInsertion(String insertion);

    <V> RawMessageBuilder setClickAction(ClickAction<V> clickAction);

    <V> RawMessageBuilder setHoverAction(HoverAction<V> hoverAction);

    RawMessageBuilder setTranslationIdentifier(String translationIdentifier);

    // TODO score api
    RawMessageBuilder setScore(Object score);

    RawMessageBuilder overrideScore(Object score, String value);

}
