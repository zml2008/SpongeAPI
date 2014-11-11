package org.spongepowered.api.text.action;

public interface ClickAction<V> extends TextAction<V> {

    interface OpenUrl extends ClickAction<String> {

    }

    interface RunCommand extends ClickAction<String> {

    }

    interface ChangePage extends ClickAction<Integer> {

    }

    interface SuggestCommand extends ClickAction<String> {

    }

}
