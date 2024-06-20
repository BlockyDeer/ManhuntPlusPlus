package com.blockydeer.manhuntplusplus;

import com.blockydeer.manhuntplusplus.util.math.RadiusRandom;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public final class CompassRightClickListener implements Listener {
    @EventHandler
    public void onCompassRightClick(@NotNull PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR || event.getItem() == null ||
                !(event.getItem().getItemMeta() instanceof CompassMeta)) {
            return;
        }
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        CompassMeta compassMeta = (CompassMeta) mainHandItem.getItemMeta();

        if (compassMeta != null) {
            GameState gameState = GameState.getGameState();
            Player nearestRunner = gameState.getNearestRunner(player);
            if (nearestRunner == null) {
                player.sendMessage("在该世界内没有找到逃脱者");
            }
            Location nearestRunnerLocation = nearestRunner.getLocation();
            Location compassPointTo;
            PluginConfig config = ManhuntPlusPlus.getInstance().getPluginConfig();
            if (config.doRandomOffset) {
                compassPointTo = RadiusRandom.radiusRandom(gameState.getRandomDouble(), gameState.getRandomDouble(), nearestRunnerLocation, config.randomOffsetRadius);
            } else {
                compassPointTo = nearestRunnerLocation;
            }
            compassMeta.setLodestone(compassPointTo);
            compassMeta.setLodestoneTracked(false);
            mainHandItem.setItemMeta(compassMeta);

            if (config.sendCompassPointToExact) {
                String msg = String.format("找到逃脱者： %s。在位置：x=%d, y=?, z=%d",
                        nearestRunner.getName(), compassPointTo.getBlockX(), compassPointTo.getBlockZ());
                player.sendMessage(ChatColor.GOLD + msg);
            }
        } else {
            ManhuntPlusPlus.getInstance().getLogger().warning("Got null compassMeta.");
        }
    }
}
