package com.blockydeer.manhuntplusplus;

import com.blockydeer.manhuntplusplus.runnable.GameRealStartRunnable;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class GameState {
    private final Random radiusRand;
    private static final GameState gameState;

    private boolean gameStart = false;
    private boolean gameReady = false;

    private final BukkitRunnable gameRealStartRunnable;
    private final Map<String, Location> runnersLocationWhenGameReady;

    private final List<String> runnerList;
    private final List<String> hunterList;

    static {
        gameState = new GameState(false);
    }

    private GameState(boolean gameStart) {
        radiusRand = new Random();
        this.gameStart = gameStart;
        runnerList = new ArrayList<>();
        hunterList = new ArrayList<>();

        gameRealStartRunnable = new GameRealStartRunnable();
        runnersLocationWhenGameReady = new HashMap<>();
    }

    public double getRandomDouble() {
        return radiusRand.nextDouble();
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
        for (Player playerIter : player.getWorld().getPlayers()) {
            if (playerIter == player)
                continue;

            double distance = player.getLocation().distance(playerIter.getLocation());
            if (distance < lastDistance) {
                lastDistance = distance;
                result = playerIter;
            }
        }

        return result;
    }

    public void startGame() {
        gameRealStartRunnable.cancel();
        gameReady = true;
        gameStart = true;

        setAllGamemode();
        sendCompass();
    }

    private void setAllGamemode() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String name = player.getName();
            if (runnerList.contains(name) || hunterList.contains(name)) {
                player.setGameMode(GameMode.SURVIVAL);
            } else {
                player.setGameMode(GameMode.SPECTATOR);
            }
            player.setInvulnerable(false);
        }
    }

    private void sendCompass() {
        if (ManhuntPlusPlus.getInstance().getPluginConfig().getCompassOnGameStart) {
            for (String playerId : hunterList) {
                Player player = Bukkit.getPlayerExact(playerId);
                if (player != null) {
                    ItemStack preparedCompass = new ItemStack(Material.COMPASS, 64);
                    player.getInventory().addItem(preparedCompass);
                }
            }
        }
    }

    public void setGameReady() {
        recordRunnersLocation();
        gameReady = true;
    }

    public boolean isGameReady() {
        if (ManhuntPlusPlus.getInstance().getPluginConfig().autoStartGame) {
            gameRealStartRunnable.runTaskTimer(ManhuntPlusPlus.getInstance(), 0, 1);
        }
        return gameReady;
    }

    private void recordRunnersLocation() {
        for (String playerId: runnerList) {
            Player player = Bukkit.getPlayerExact(playerId);
            if (player == null) {
                continue;
            }
            runnersLocationWhenGameReady.put(playerId, player.getLocation());
        }
    }

    public Map<String, Location> getRunnersLocationWhenGameReady() {
        return runnersLocationWhenGameReady;
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
