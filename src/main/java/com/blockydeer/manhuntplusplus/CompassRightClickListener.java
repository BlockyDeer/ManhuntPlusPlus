package com.blockydeer.manhuntplusplus;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.jetbrains.annotations.NotNull;

public class CompassRightClickListener implements Listener {
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
            Location nearestRunnerLocation = GameState.getGameState().getNearestRunnerLocation(player);
            if (nearestRunnerLocation.getWorld() != null) {
                compassMeta.setLodestone(nearestRunnerLocation);

                compassMeta.setLodestoneTracked(true);

                mainHandItem.setItemMeta(compassMeta);
            } else {
                event.getPlayer().sendMessage("在该世界内没有找到速通者");
            }
        } else {
            ManhuntPlusPlus.getInstance().getLogger().warning("Got null compassMeta.");
        }
    }
}
