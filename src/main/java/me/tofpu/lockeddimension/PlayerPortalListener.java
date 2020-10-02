package me.tofpu.lockeddimension;

import me.tofpu.lockeddimension.modules.DimensionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerPortalListener implements Listener {
    private final LockedDimension lockedDimension;
    
    public PlayerPortalListener(LockedDimension lockedDimension) {
        this.lockedDimension = lockedDimension;
    }
    
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {
        Player player = e.getPlayer();
        String world = e.getTo().getWorld().getName();
        DimensionManager manager = lockedDimension.getManager();
    
        if (manager.worldExists(world)) {
            if (manager.getWorldStatus(world)){
                e.setCancelled(true);
                manager.lock(player, world);
                return;
            }
            if (!manager.hasPermission(player, world)) {
                e.setCancelled(true);
                manager.deny(player, world);
            } else {
                manager.success(player, world);
            }
        }
    }
}