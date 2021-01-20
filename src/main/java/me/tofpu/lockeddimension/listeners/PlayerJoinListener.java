package me.tofpu.lockeddimension.listeners;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final LockedDimension lockedDimension;

    public PlayerJoinListener(LockedDimension lockedDimension) {
        this.lockedDimension = lockedDimension;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
//        if (e.getPlayer().isOp() && notifyUpdates() && lockedDimension.getUpdater().isUpdateAvailable()){
//            e.getPlayer().sendMessage(UtilsHelper.prefixColorize("&7You are currently running an older version of &fLockedDimension&7!"));
//            e.getPlayer().sendMessage(UtilsHelper.prefixColorize(String.format("&7You can download the latest version at: &f%s", lockedDimension.getUrl())));
//        }
    }

    public final boolean notifyUpdates(){
        return lockedDimension.getConfig().getBoolean("settings.notify-updates");
    }
}
