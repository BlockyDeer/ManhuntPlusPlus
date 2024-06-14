package com.blockydeer.manhuntplusplus;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class GameState {
    private static final GameState gameState;
    private boolean gameStart = false;

    private final List<String> runnerList;
    private final List<String> hunterList;

    static {
        gameState = new GameState(false);
    }

    private GameState(boolean gameStart) {
        this.gameStart = gameStart;
        runnerList = new ArrayList<>();
        hunterList = new ArrayList<>();
    }

    public void updateRunner(String playerId) {
        runnerList.remove(playerId);
        runnerList.add(playerId);
    }

    public void updateHunter(String playerId) {
        hunterList.remove(playerId);
        hunterList.add(playerId);
    }

    public boolean isGameNotStart() {
        return !gameStart;
    }

    public @Nullable Player getNearestRunner(@NotNull Player player) {
        Player result = null;
        double lastDistance = Double.MAX_VALUE;
        for(Player playerIter : player.getWorld().getPlayers()) {
            if(playerIter == player)
                continue;

            double distance = player.getLocation().distance(playerIter.getLocation());
            if(distance < lastDistance) {
                lastDistance = distance;
                result = playerIter;
            }
        }

        return result;
    }

    public void startGame() {
        gameStart = true;

        for (Player player: Bukkit.getOnlinePlayers()) {
            String name = player.getName();
            if (runnerList.contains(name) || hunterList.contains(name)) {
                player.setGameMode(GameMode.SURVIVAL);
            } else {
                player.setGameMode(GameMode.SPECTATOR);
            }
            player.setInvulnerable(false);
        }
    }

    public static GameState getGameState() {
        return gameState;
    }

    public List<String> getRunnerList() {
        return runnerList;
    }

    public List<String> getHunterList() {
        return hunterList;
    }
}
