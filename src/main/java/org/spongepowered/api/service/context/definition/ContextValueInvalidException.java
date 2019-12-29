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

import java.util.Set;

/**
 * Thrown when a context value is invalid for the given context definition
 */
public class ContextValueInvalidException extends Exception {
    private static final long serialVersionUID = -400138690909271339L;
    
    private final String providedValue;
    private final Set<String> suggestedValues;

    public ContextValueInvalidException(String providedValue, Set<String> suggestedValues) {
        this.providedValue = providedValue;
        this.suggestedValues = ImmutableSet.copyOf(suggestedValues);
    }

    public ContextValueInvalidException(String providedValue) {
        this(providedValue, ImmutableSet.of());
    }

    /**
     * @return the value provided by the user
     */
    public String getProvidedValue() {
        return providedValue;
    }

    /**
     * Other potentially relevant values that the user may be interested in
     *
     * @return an immutable set of values
     */
    public Set<String> getSuggestedValues() {
        return suggestedValues;
    }
}
