package me.tofpu.lockeddimension.modules;

import me.tofpu.lockeddimension.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DimensionManager {
    private FileConfiguration config;
    protected String worldName = "";
    
    private static final ArrayList<Dimension> dimensions = new ArrayList<>();
    
    public DimensionManager(FileConfiguration config){
        this.config = config;
        for(String key : config.getConfigurationSection("dimensions").getKeys(false)) {
            worldName = key;
            Options options = new Options(key, isLocked(), getPermission(), getDeniedMessage(), getSucceedMessage(), getLockedMessage());
            Dimension dimension = new Dimension(options);
            dimensions.add(dimension);
        }
    }
    
    public void reload(FileConfiguration config){
        dimensions.clear();
        this.config = config;
        for(String key : config.getConfigurationSection("dimensions").getKeys(false)) {
            worldName = key;
            Options options = new Options(key, isLocked(), getPermission(), getDeniedMessage(), getSucceedMessage(), getLockedMessage());
            Dimension dimension = new Dimension(options);
            dimensions.add(dimension);
        }
    }
    
    public boolean hasPermission(Player player, String worldName){
        for(Dimension dimension : dimensions){
            Options options = dimension.getOptions();
            if (options.getWorldName().equalsIgnoreCase(worldName)) {
                return player.hasPermission(options.getPermission());
            }
        }
        return false;
    }
    
    public boolean worldExists(String worldName){
        for(Dimension dimension : dimensions){
            Options options = dimension.getOptions();
            if (options.getWorldName().equalsIgnoreCase(worldName)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getWorldStatus(String worldName){
        for(Dimension dimension : dimensions){
            Options options = dimension.getOptions();
            if (options.getWorldName().equalsIgnoreCase(worldName)) {
                return options.isLocked();
            }
        }
        return false;
    }
    
    public void success(Player player, String worldName){
        for(Dimension dimension : dimensions){
            Options options = dimension.getOptions();
            if (options.getWorldName().equalsIgnoreCase(worldName)) {
                String success = options.getSucceedMessage();
                if (success != null && !success.isEmpty())
                player.sendMessage(Utils.color(success));
            }
        }
    }
    
    public void deny(Player player, String worldName){
        for(Dimension dimension : dimensions){
            Options options = dimension.getOptions();
            if (options.getWorldName().equalsIgnoreCase(worldName)) {
                String locked = options.getDeniedMessage();
                if (locked != null && !locked.isEmpty())
                    player.sendMessage(Utils.color(locked));
            }
        }
    }
    
    public void lock(Player player, String worldName){
        for(Dimension dimension : dimensions){
            Options options = dimension.getOptions();
            if (options.getWorldName().equalsIgnoreCase(worldName)) {
                String disabled = options.getLockedMessage();
                if (disabled != null && !disabled.isEmpty())
                    player.sendMessage(Utils.color(disabled));
            }
        }
    }
    
    public boolean isLocked(){
        return config.getBoolean("dimensions." + worldName + ".lock");
    }
    
    public String getPermission(){
        return config.getString("dimensions." + worldName + ".permission");
    }
    
    public String getDeniedMessage(){
        return config.getString("dimensions." + worldName + ".denied-message");
    }
    
    public String getSucceedMessage(){
        return config.getString("dimensions." + worldName + ".succeed-message");
    }
    
    public String getLockedMessage(){
        return config.getString("dimensions." + worldName + ".locked-message");
    }
    
    public static ArrayList<Dimension> getDimensions() {
        return dimensions;
    }
}
