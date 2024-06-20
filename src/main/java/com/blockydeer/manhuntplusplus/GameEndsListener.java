package com.blockydeer.manhuntplusplus;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class GameEndsListener implements Listener {
    @EventHandler
    public void onEntityDeath(@NotNull EntityDeathEvent event) {
        if (GameState.getGameState().isGameNotStart()) {
            return;
        }
        PluginConfig config = ManhuntPlusPlus.getInstance().getPluginConfig();
        if (config.runnerWinCondition == RunnerWinCondition.ENDER_DRAGON && event.getEntity().getType() == EntityType.ENDER_DRAGON) {
            GameState.getGameState().runnerWin();
        }
    }

    @EventHandler
    public void onPlayerEnterPortal(@NotNull PlayerPortalEvent event) {
        if (ManhuntPlusPlus.getInstance().getPluginConfig().runnerWinCondition != RunnerWinCondition.END_POEM ||
                GameState.getGameState().isGameNotStart()) {
            return;
        }
        World worldFrom = event.getFrom().getWorld();
        World worldTo = Objects.requireNonNull(event.getTo()).getWorld();
        if (worldFrom == null || worldTo == null) {
            ManhuntPlusPlus.getInstance().getLogger().warning("PlayerPortalEvent get unexpected null.");
            return;
        }
        World.Environment worldFromEnvironment = worldFrom.getEnvironment();
        World.Environment worldToEnvironment = worldTo.getEnvironment();
        if (worldFromEnvironment == World.Environment.THE_END && worldToEnvironment == World.Environment.NORMAL &&
                GameState.getGameState().getRunnerList().contains(event.getPlayer().getName())) {
            GameState.getGameState().runnerWin();
        }
    }
}
