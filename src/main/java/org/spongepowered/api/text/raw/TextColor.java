package org.spongepowered.api.text.raw;

import com.google.common.base.Optional;

import java.awt.*;

public interface TextColor {

    // get color
    Color getColor();

    /**
     * For colors that support it(meaning default Minecraft ones),
     * get the color code.
     *
     * @return the Minecraft color code
     */
    @Deprecated Optional<Character> getCode();

}
