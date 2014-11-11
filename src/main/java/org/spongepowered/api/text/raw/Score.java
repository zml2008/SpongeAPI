package org.spongepowered.api.text.raw;

import com.google.common.base.Optional;

public interface Score {

    String getPlayerName();

    // TODO use Objective
    Object getObjective();

    Optional<String> getValue();

}
