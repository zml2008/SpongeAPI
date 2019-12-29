/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
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
package org.spongepowered.api.service.context;

import org.spongepowered.api.service.context.definition.ContextValueInvalidException;
import org.spongepowered.api.service.context.definition.SimpleContextDefinition;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface ContextDefinition<T, ContextHolder extends Contextual<ContextHolder>> {

    /**
     * Get the identifier for this context. This identifier must match the expression {@code [a-zA-Z:-]+}.
     *
     * @return The context key
     */
    String getName();

    /**
     * Create a new context value instance for this type of context
     *
     * @param value The value to associate with this context
     * @return The resulting context value
     */
    default Context<T, ContextHolder> createValue(T value) {
        return new Context<>(this, value);
    }

    /**
     * Takes a given context value and serializes it to a canonical string
     * Given an object {@code o} of type {@code T}, {@code deserialize{serialize(o)} == o} must be true.
     *
     * @param userValue The value to serialize
     * @return A representation
     */
    String serialize(T userValue);

    /**
     * Given a user-provided value, resolve it to a context value.
     * This may use current machine state for its information.
     * This parsing is expected to be fairly loose -- this method may accept for example command input.
     *
     * Given an object {@code o} of type {@code T}, {@code deserialize{serialize(o)} == o} must be true.
     *
     * @param rawValue The string value
     * @return A deserialized value
     * @throws ContextValueInvalidException If the provided text could not be resolved to a valid context.
     */
    T deserialize(String rawValue) throws ContextValueInvalidException;

    default boolean matches(Context<T, ContextHolder> otherCtx, T activeValue) {
        try {
            return matches(otherCtx.getParsedValue(this), activeValue);
        } catch (ContextValueInvalidException e) {
            return false;
        }
    }

    /**
     * Presuming {@code ownVal} is a stored context, returns whether {@code testVal} could be included in this context.
     * @param ownVal The current value associated with this context definition
     * @param testVal The value to check for a match
     * @return Whether a match was found
     */
    boolean matches(T ownVal, T testVal);

    /**
     * For all value of this context definition that apply to the provided subject
     * @param subject The subject to query active contexts for
     * @param valueConsumer A callback function that will be called once for every found context
     */
    void accumulateCurrentValues(ContextHolder subject, Consumer<T> valueConsumer);

    /**
     * Provide
     * @param subject The context holder to query for
     * @return
     */
    Set<T> suggestValues(ContextHolder subject);

    public static <TContextual extends Contextual<TContextual>> ContextDefinition<String, TContextual> forSimple(String key, BiConsumer<TContextual, Consumer<String>> accumulatorFunc) {
        return new SimpleContextDefinition<>(key, accumulatorFunc);
    }
}
