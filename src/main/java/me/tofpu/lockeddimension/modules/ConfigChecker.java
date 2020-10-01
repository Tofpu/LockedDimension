package me.tofpu.lockeddimension.modules;

import me.tofpu.lockeddimension.LockedDimension;
import org.bukkit.Sound;
import org.bukkit.World;

public class ConfigChecker {
    private final LockedDimension lockedDimension;
    
    public ConfigChecker(LockedDimension lockedDimension) {
        this.lockedDimension = lockedDimension;
    }
    
    public void check(String world, String succeed, String denied, String locked){
        if (getCheckError()){
            if (!isWorldExist(world)){
                warn(world + " world does not exist. make sure its spelled correctly.");
            }
            if (!isSoundExist(succeed)){
                warn(succeed + " sound does not exist. make sure its spelled correctly.");
            }
            if (!isSoundExist(denied)){
                warn(denied + " sound does not exist. make sure its spelled correctly.");
            }
            if (!isSoundExist(locked)){
                warn(locked + " sound does not exist. make sure its spelled correctly.");
            }
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
    
    public boolean isSoundExist(String key){
        try {
            Sound sound = Sound.valueOf(key);
            return true;
        } catch (IllegalArgumentException ignored){
            return false;
        }
    }
    
    public void warn(String message){
        lockedDimension.getLogger().warning(message);
    }
    
    public boolean getCheckError(){
        return lockedDimension.getConfig().getBoolean("settings.check-errors");
    }
}
