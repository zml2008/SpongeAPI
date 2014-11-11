package org.spongepowered.api.text;

import com.google.common.base.Optional;

import java.util.List;

public interface SingleTextFormat extends TextFormat {

    String getName();

    /**
     * Gets the corresponding Minecraft formatting code, that, when applied,
     * has the same effect as this text format.
     *
     * @return a List of Minecraft formatting codes
     */
    @Deprecated
    Optional<Character> getCode();

}
