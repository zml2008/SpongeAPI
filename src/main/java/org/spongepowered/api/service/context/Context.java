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

import static java.util.Objects.requireNonNull;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.service.context.definition.ContextValueInvalidException;

import java.util.Objects;
import java.util.Optional;

/**
 * Encapsulates a single attribute about the state or circumstances of a
 * {@link Contextual}.
 *
 * <p>A {@link Contextual}'s overall "context" is made up multiple
 * {@link Context} instances, usually stored together in a
 * {@link java.util.Set}.</p>
 *
 * <p>Any single {@link Context} attribute is made up of a <b>key</b> and a
 * <b>value</b>. The key represents the type of context, and the value is just
 * that, the value associated with the key. Some common/shared keys are
 * expressed as static fields on this class for convenience.</p>
 *
 * <p>For example, a context encapsulating a {@link Contextual}s circumstance
 * within a given world would have key of "world" and a value equal to the name
 * of the world.</p>
 */
public final class Context<T, ContextHolder extends Contextual<ContextHolder>> {

    /*public static final String USER_KEY = "user";
    public static final String WORLD_KEY = "world";
    public static final String DIMENSION_KEY = "dimension";
    public static final String REMOTE_IP_KEY = "remoteip";
    public static final String LOCAL_HOST_KEY = "localhost";
    public static final String LOCAL_IP_KEY = "localip";
    public static final String LOCAL_PORT_KEY = "localport";*/

    private final String key;
    private final String rawValue;

    @Nullable
    private ContextDefinition<T, ContextHolder> definition;
    @Nullable
    private T parsedValue;

    /**
     * Create a new unresolved context instance
     *
     * @param key Context key. Must not be null.
     * @param rawValue Context value. Must not be null.
     */
    public Context(String key, String rawValue) {
        requireNonNull(key, "key");
        requireNonNull(rawValue, "value");
        this.key = key;
        this.rawValue = rawValue;
    }

    Context(ContextDefinition<T, ContextHolder> type, T value) {
        this.key = type.getName();
        this.rawValue = type.serialize(value);
        this.definition = type;
        this.parsedValue = value;
    }

    /**
     * Gets the context key.
     *
     * @return The key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Gets the context value.
     *
     * @return The value
     */
    public String getRawValue() {
        return this.rawValue;
    }

    public boolean tryResolve(ContextualService<ContextHolder> service) {
        if (this.definition != null) {
            return this.parsedValue != null;
        }

        Optional<ContextDefinition<T, ContextHolder>> def = service.getContextDefinition(this.key);
        if (def.isPresent()) {
            this.definition = def.get();
            try {
                this.parsedValue = requireNonNull(def.get().deserialize(this.rawValue));
                return true;
            } catch (ContextValueInvalidException e) {
                return false;
            }
        }
        return false;
    }

    public T getParsedValue(ContextDefinition<T, ContextHolder> definition) throws ContextValueInvalidException {
        if (this.definition != null && this.definition != definition) {
            throw new IllegalStateException("The provided context definition does not matcch the one this context object currently knows about");
        }

        this.definition = definition;
        @Nullable
        T parsedValue = this.parsedValue;
        if (parsedValue == null) {
            this.parsedValue = (parsedValue = definition.deserialize(this.rawValue));
        }
        return parsedValue;
    }

    public T getParsedValue(ContextualService<ContextHolder> service) throws ContextValueInvalidException {
        @Nullable
        T tempParsed = this.parsedValue;

        if (tempParsed != null) {
            return tempParsed;
        }

        ContextDefinition<T, ContextHolder> def = service.<T>getContextDefinition(this.key)
                .orElseThrow(() -> new IllegalStateException("No definition found for context " + this.key));
        return parsedValue = def.deserialize(this.rawValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Context<?, ?>)) {
            return false;
        }
        Context<?, ?> otherCtx = ((Context) o);

        return Objects.equals(key, otherCtx.key) &&
                (Objects.equals(rawValue, otherCtx.rawValue)
                || Objects.equals(parsedValue, otherCtx.parsedValue));
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + rawValue.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getKey() + "=" + parsedValue + " (raw: " + rawValue + ")";
    }
}
