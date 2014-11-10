package org.spongepowered.api.text.raw;

import com.google.common.base.Optional;

public interface TextColor {

    int getRed();

    int getGreen();

    int getBlue();

    /**
     * For colors that support it(meaning default Minecraft ones),
     * get the color code.
     *
     * @return the Minecraft color code
     */
    @Deprecated Optional<Integer> getCode();

}
