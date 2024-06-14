package com.blockydeer.manhuntplusplus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class PluginConfig {
    public boolean doRandomOffset = false;
    public double randomOffsetRadius = 15;
    public void init(@NotNull JavaPlugin plugin) {
        FileConfiguration config = plugin.getConfig();
        doRandomOffset = config.getBoolean("DoRandomOffset");
        randomOffsetRadius = config.getInt("RandomOffsetRadius");
    }
}
