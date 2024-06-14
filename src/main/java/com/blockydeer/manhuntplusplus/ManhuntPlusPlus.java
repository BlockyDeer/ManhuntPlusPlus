package com.blockydeer.manhuntplusplus;

import com.blockydeer.manhuntplusplus.command.GameCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ManhuntPlusPlus extends JavaPlugin {
    private static ManhuntPlusPlus plugin;
    @Override
    public void onEnable() {
        plugin = this;
        Objects.requireNonNull(getCommand("manhunt")).setExecutor(new GameCommand());

        getServer().getPluginManager().registerEvents(new GameLobby(), this);
        getServer().getPluginManager().registerEvents(new CompassRightClickListener(), this);
    }

    @Override
    public void onDisable() {

        plugin = null;
    }

    public static ManhuntPlusPlus getInstance() {
        return plugin;
    }
}
