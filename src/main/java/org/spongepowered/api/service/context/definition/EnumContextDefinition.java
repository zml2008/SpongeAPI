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
import org.spongepowered.api.service.context.ContextDefinition;
import org.spongepowered.api.service.context.Contextual;

import java.util.Objects;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public abstract class EnumContextDefinition<V extends Enum<V>, TContextual extends Contextual<TContextual>> implements ContextDefinition<V, TContextual> {
    private final String name;
    private final Class<V> enumClass;

    public EnumContextDefinition(String name, Class<V> enumClass) {
        this.name = requireNonNull(name, "name");
        this.enumClass = enumClass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String serialize(V userValue) {
        return userValue.name();
    }

    @Override
    public V deserialize(String rawValue) {
        return Enum.valueOf(enumClass, rawValue.toUpperCase());
    }

    @Override
    public boolean matches(V ownVal, V testVal) {
        return Objects.equals(ownVal, testVal);
    }

    @Override
    public Set<V> suggestValues(TContextual subject) {
        return ImmutableSet.copyOf(enumClass.getEnumConstants());
    }
}
