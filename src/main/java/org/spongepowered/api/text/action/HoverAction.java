package org.spongepowered.api.text.action;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.item.ItemType;

import java.util.UUID;

public interface HoverAction<V> extends TextAction<V> {

    interface ShowText extends HoverAction<String> {

    }

    interface ShowItem extends HoverAction<ItemType> {

    }

    // TODO replace with achievement
    interface ShowAchievement extends HoverAction<Object> {

    }

    interface ShowEntity extends HoverAction<Entity> {

        // TODO keep this just for reference, but should use entity?
        interface ShowActionEntity{

            String getName();

            String getType();

            UUID getID();

        }

    }

}
