package org.spongepowered.api.text.raw;

public interface ClickAction {

    String getName();

    Object getValue();

    interface OpenUrl extends ClickAction {

    }



}
