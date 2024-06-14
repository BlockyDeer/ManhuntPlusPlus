package com.blockydeer.manhuntplusplus.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class PlayerLocation {
    public static @NotNull Location getLocationByName(String playerId) {
        return Objects.requireNonNull(Bukkit.getPlayerExact(playerId)).getLocation();
    }
}
