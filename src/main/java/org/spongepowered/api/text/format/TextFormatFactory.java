package org.spongepowered.api.text.format;

import java.util.List;

interface TextFormatFactory {

    TextColor parseColor(String name);

    List<TextColor> getColors();

    TextStyle parseStyle(String name);

    List<TextStyle> getStyles();

    TextStyle createStyle(TextStyle[] styles);

}
