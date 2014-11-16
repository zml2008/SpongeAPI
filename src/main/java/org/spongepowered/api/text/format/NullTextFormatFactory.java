package org.spongepowered.api.text.format;

import java.util.List;

class NullTextFormatFactory implements TextFormatFactory {
    @Override
    public TextColor parseColor(String name) {
        return null;
    }

    @Override
    public List<TextColor> getColors() {
        return null;
    }

    @Override
    public TextStyle parseStyle(String name) {
        return null;
    }

    @Override
    public List<TextStyle> getStyles() {
        return null;
    }

    @Override
    public TextStyle createStyle(TextStyle[] styles) {
        return null;
    }
}
