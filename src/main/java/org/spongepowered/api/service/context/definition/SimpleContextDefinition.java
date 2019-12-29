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
package org.spongepowered.api.service.context.definition;

import com.google.common.collect.ImmutableSet;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.service.context.ContextDefinition;
import org.spongepowered.api.service.context.Contextual;

import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class SimpleContextDefinition<TContextual extends Contextual<TContextual>> implements ContextDefinition<String, TContextual> {
    private final String name;
    @Nullable
    private final BiConsumer<TContextual, Consumer<String>> accumulator;

    public SimpleContextDefinition(String name) {
        this.name = requireNonNull(name, "name");
        accumulator = null;
    }

    public SimpleContextDefinition(String name, BiConsumer<TContextual, Consumer<String>> accumulator) {
        this.name = requireNonNull(name, "name");
        this.accumulator = accumulator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String serialize(String userValue) {
        return userValue;
    }

    @Override
    public String deserialize(String rawValue) {
        return rawValue;
    }

    @Override
    public boolean matches(String ownVal, String testVal) {
        return Objects.equals(ownVal, testVal);
    }

    @Override
    public void accumulateCurrentValues(TContextual subject, Consumer<String> valueConsumer) {
        if (this.accumulator != null) {
            accumulator.accept(subject, valueConsumer);
        }
    }

    @Override
    public Set<String> suggestValues(TContextual subject) {
        return ImmutableSet.of();
    }
}
