package me.tofpu.lockeddimension.config;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.utils.XSound;
import org.bukkit.Sound;
import org.bukkit.World;

public class ConfigChecker {
    private final LockedDimension lockedDimension;
    
    public ConfigChecker(LockedDimension lockedDimension) {
        this.lockedDimension = lockedDimension;
    }
    
    public void check(String world, String succeed, String denied, String locked){
        if (!isCheckErrorEnabled()) {
            return;
        }

        if (!isWorldExist(world)){
            warn(world + " world does not exist. make sure its spelled correctly.");
        }

        if (isSoundNull(succeed)){
            warn(succeed + " sound does not exist. make sure its spelled correctly.");
        }

        if (isSoundNull(denied)){
            warn(denied + " sound does not exist. make sure its spelled correctly.");
        }

        if (isSoundNull(locked)){
            warn(locked + " sound does not exist. make sure its spelled correctly.");
        }
    }
    
    public boolean isWorldExist(String key){
        for(World world : lockedDimension.getServer().getWorlds()){
            if (world.getName().equalsIgnoreCase(key)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isSoundNull(String key){
        return XSound.parse(key) == null;
    }
    
    public void warn(String message){
        lockedDimension.getLogger().warning(message);
    }
    
    public boolean isCheckErrorEnabled(){
        return lockedDimension.getConfig().getBoolean("settings.check-errors");
    }
}
