package me.tofpu.lockeddimension.listeners;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.modules.Dimension;
import me.tofpu.lockeddimension.modules.manager.DimensionManager;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerPortalListener implements Listener {
    private final LockedDimension lockedDimension;
    
    public PlayerPortalListener(final LockedDimension lockedDimension) {
        this.lockedDimension = lockedDimension;
    }

    @EventHandler(ignoreCancelled = true)
    public void on(PlayerTeleportEvent event) {
        final Player player = event.getPlayer();
        if (event.getTo() == null) {
            return;
        }
        World world = event.getTo().getWorld();
        if (world == null) {
            return;
        }
        handle(event, world, player);
    }

    private void handle(Cancellable event, World targetWorld, Player player) {
        final String world = targetWorld.getName();

        final DimensionManager manager = lockedDimension.getManager();
        final Dimension dimension = manager.getDimension(world);

        if (dimension == null) {
            return;
        }

        if (dimension.getOptions().isLocked()){
            event.setCancelled(true);
            manager.lock(dimension, player, world);
            return;
        }

        if (!UtilsHelper.hasPermission(dimension, player, world)) {
            event.setCancelled(true);
            manager.deny(dimension, player, world);
        }
    }
}
