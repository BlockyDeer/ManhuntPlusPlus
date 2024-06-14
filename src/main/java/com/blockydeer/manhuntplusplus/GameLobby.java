package com.blockydeer.manhuntplusplus;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public final class GameLobby implements Listener {
    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        if (GameState.getGameState().isGameNotStart()) {
            Player player = event.getPlayer();
            player.setGameMode(GameMode.ADVENTURE);
            player.setInvulnerable(true);
        }
    }

    @EventHandler
    public void onPlayerDamage(@NotNull EntityDamageByEntityEvent event) {
        if (GameState.getGameState().isGameNotStart()) {
            event.setCancelled(true);
        }
    }
}
