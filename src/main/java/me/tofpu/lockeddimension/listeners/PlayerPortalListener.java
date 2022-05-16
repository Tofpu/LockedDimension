package me.tofpu.lockeddimension.listeners;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.modules.Dimension;
import me.tofpu.lockeddimension.modules.manager.DimensionManager;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerPortalListener implements Listener {
    private final LockedDimension lockedDimension;
    
    public PlayerPortalListener(final LockedDimension lockedDimension) {
        this.lockedDimension = lockedDimension;
    }
    
    @EventHandler
    public void onPlayerPortal(final PlayerPortalEvent e) {
        final Player player = e.getPlayer();
        final String world = e.getTo().getWorld().getName();
        
        final DimensionManager manager = lockedDimension.getManager();
        final Dimension dimension = manager.getDimension(world);

        if (dimension == null) {
            return;
        }

        if (dimension.getOptions().isLocked()){
            e.setCancelled(true);
            manager.lock(dimension, player, world);
            return;
        }

        if (!UtilsHelper.hasPermission(dimension, player, world)) {
            e.setCancelled(true);
            manager.deny(dimension, player, world);
        } else {
            manager.success(dimension, player, world);
        }
    }
}
