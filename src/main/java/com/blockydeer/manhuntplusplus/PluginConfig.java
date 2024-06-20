package com.blockydeer.manhuntplusplus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class PluginConfig {
    public boolean doRandomOffset = false;
    public double randomOffsetRadius = 10;
    public boolean getCompassOnGameStart = true;
    public boolean sendCompassPointToExact = true;
    public boolean autoStartGame = true;
    public int autoStartRadius = 5;

    public void init(@NotNull JavaPlugin plugin) {
        FileConfiguration config = plugin.getConfig();
        doRandomOffset = config.getBoolean("DoRandomOffset");
        randomOffsetRadius = config.getInt("RandomOffsetRadius");
        getCompassOnGameStart = config.getBoolean("GetCompassOnGameStart");
        sendCompassPointToExact = config.getBoolean("SendCompassPointToExact");
        autoStartGame = config.getBoolean("AutoStartGame");
        autoStartRadius = config.getInt("AutoStartRadius");
    }
}
