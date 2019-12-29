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
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.context.ContextDefinition;
import org.spongepowered.api.service.context.Contextual;

import java.util.Set;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class CatalogTypeContextDefinition<V extends CatalogType, TContextual extends Contextual<TContextual>> implements ContextDefinition<V, TContextual> {
    private final String name;
    private final Class<V> typeClass;
    protected CatalogTypeContextDefinition(String name, Class<V> typeClass) {
        this.name = requireNonNull(name, "name");
        this.typeClass = typeClass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String serialize(V userValue) {
        return userValue.getKey().toString();
    }

    @Override
    public V deserialize(String rawValue) throws ContextValueInvalidException {
        String[] namespaced = rawValue.split(":", 2);
        ResourceKey key;
        if (namespaced.length == 2) {
            key = ResourceKey.of(namespaced[0], namespaced[1]);
        } else {
            key = ResourceKey.minecraft(rawValue);
        }

        return Sponge.getRegistry().getCatalogRegistry().get(typeClass, key)
                .orElseThrow(() -> new ContextValueInvalidException(rawValue));
    }

    @Override
    public boolean matches(V ownVal, V testVal) {
        return ownVal.equals(testVal);
    }

    @Override
    public void accumulateCurrentValues(TContextual subject, Consumer<V> valueConsumer) {

    }

    @Override
    public Set<V> suggestValues(TContextual subject) {
        return ImmutableSet.copyOf(Sponge.getRegistry().getCatalogRegistry().getAllOf(typeClass));
    }
}
