package me.tofpu.lockeddimension.config;

import me.tofpu.lockeddimension.modules.manager.DimensionManager;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigValues {
    private FileConfiguration config;
    private final String PATH = DimensionManager.getPATH();
    private String worldName;
    
    public ConfigValues(FileConfiguration config){
        this.config = config;
    }
    
    public void setConfig(FileConfiguration config) {
        this.config = config;
    }
    
    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }
    
    public final boolean isLocked(){
        return config.getBoolean(PATH + worldName + ".lock");
    }
    
    public final String getPermission(){
        return config.getString(PATH + worldName + ".permission");
    }
    
    public final String getDeniedMessage(){
        return config.getString(PATH + worldName + ".denied-message");
    }
    
    public final String getSucceedMessage(){
        return config.getString(PATH + worldName + ".succeed-message");
    }
    
    public final String getLockedMessage(){
        return config.getString(PATH + worldName + ".locked-message");
    }
    
    public final String getBroadcastMessage(){
        return config.getString(PATH + worldName + ".broadcast-message");
    }
    
    public final String getSucceedSound(){
        return config.getString(PATH + worldName + ".succeed-sound");
    }
    
    public final String getDeniedSound(){
        return config.getString(PATH + worldName + ".denied-sound");
    }
    
    public final String getLockedSound(){
        return config.getString(PATH + worldName + ".locked-sound");
    }
}
