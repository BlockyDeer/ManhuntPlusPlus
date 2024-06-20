package com.blockydeer.manhuntplusplus.runnable;

import com.blockydeer.manhuntplusplus.GameState;
import com.blockydeer.manhuntplusplus.ManhuntPlusPlus;
import com.blockydeer.manhuntplusplus.PluginConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class GameRealStartRunnable extends BukkitRunnable {
    @Override
    public void run() {
        GameState gameState = GameState.getGameState();
        if (!gameState.isGameReady()) {
            return;
        }
        PluginConfig config = ManhuntPlusPlus.getInstance().getPluginConfig();
        for (String playerId : gameState.getRunnerList()) {
            Player player = Bukkit.getPlayerExact(playerId);
            if (player == null) {
                continue;
            }
            for (Map.Entry<String, Location> entry : gameState.getRunnersLocationWhenGameReady().entrySet()) {
                if (entry.getValue().distance(player.getLocation()) >= config.autoStartRadius) {
                    gameState.startGame();

                }
            }
        }
    }
}
