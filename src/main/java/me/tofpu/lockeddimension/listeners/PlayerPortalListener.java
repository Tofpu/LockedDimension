package me.tofpu.lockeddimension.listeners;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.config.action.Action;
import me.tofpu.lockeddimension.config.action.type.ActionType;
import me.tofpu.lockeddimension.config.action.type.register.TypeRegister;
import me.tofpu.lockeddimension.modules.dimension.Dimension;
import me.tofpu.lockeddimension.modules.dimension.DimensionValues;
import me.tofpu.lockeddimension.modules.dimension.manager.DimensionManager;
import me.tofpu.lockeddimension.utils.UtilsHelper;
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
        Dimension dimension = manager.getDimension(world);

        if (dimension == null) {
            return;
        }

        DimensionValues values = dimension.getDimensionValues();
        Action action = values.isLocked() ? values.getLocked() : !UtilsHelper.hasPermission(player, world) ? values.getDenied() : values.getSucceed();

        if (action != values.getSucceed()){
            e.setCancelled(true);
        }

        for (String i : action.getAction()) {
            String[] args = i.split(" ");

            ActionType actionType = TypeRegister.get(args[0]);
            if (actionType == null) {
                continue;
            }
            actionType.playAction(player, args);
        }
    }
}
