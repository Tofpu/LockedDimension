package me.tofpu.lockeddimension.modules;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.Utils;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DimensionManager {
    private final LockedDimension lockedDimension;
    
    private FileConfiguration config;
    private ConfigChecker configChecker;
    protected String worldName = "";
    
    private static final ArrayList<Dimension> dimensions = new ArrayList<>();
    
    public DimensionManager(LockedDimension lockedDimension, FileConfiguration config){
        this.lockedDimension = lockedDimension;
        this.configChecker = new ConfigChecker(lockedDimension);
        this.config = config;
        for(String key : config.getConfigurationSection("dimensions").getKeys(false)) {
            worldName = key;
            configChecker.check(key, getSucceedSound(), getDeniedSound(), getLockedSound());
            
            Options options = new Options(key, isLocked(), getPermission(), getSucceedMessage(), getDeniedMessage(), getLockedMessage(), getBroadcastMessage(), getSucceedSound(), getDeniedSound(), getLockedSound());
            Dimension dimension = new Dimension(options);
            dimensions.add(dimension);
        }
    }
    
    public void reload(FileConfiguration config){
        dimensions.clear();
        this.config = config;
        for(String key : config.getConfigurationSection("dimensions").getKeys(false)) {
            worldName = key;
            configChecker.check(key, getSucceedSound(), getDeniedSound(), getLockedSound());
            
            Options options = new Options(key, isLocked(), getPermission(), getSucceedMessage(), getBroadcastMessage(), getDeniedMessage(), getLockedMessage(), getSucceedSound(), getDeniedSound(), getLockedSound());
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
                String broadcast = options.getBroadcastMessage();
                String sound = options.getSucceedSound();
                if (lockedDimension.isEnabledPAPI()){
                    success = Utils.parse(player, options.getSucceedMessage());
                }
                if (lockedDimension.isEnabledPAPI()){
                    broadcast = Utils.parse(player, options.getBroadcastMessage());
                }
                if (sound != null) {
                    try {
                        Sound enumSound = Sound.valueOf(sound);
                        player.playSound(player.getLocation(), enumSound, 1, 1);
                    } catch (IllegalArgumentException e) {
                        lockedDimension.getLogger().warning(sound + " does not exist.");
                    }
                }
                if (success != null && !success.isEmpty()) {
                    player.sendMessage(Utils.color(success));
                }
                if (broadcast != null && !broadcast.isEmpty()) {
                    player.sendMessage(Utils.color(broadcast));
                }
            }
        }
    }
    
    public void deny(Player player, String worldName){
        for(Dimension dimension : dimensions){
            Options options = dimension.getOptions();
            if (options.getWorldName().equalsIgnoreCase(worldName)) {
                String locked = options.getDeniedMessage();
                String sound = options.getDeniedSound();
                if (lockedDimension.isEnabledPAPI()){
                    locked = Utils.parse(player, options.getDeniedMessage());
                }
                if (sound != null) {
                    try {
                        Sound enumSound = Sound.valueOf(sound);
                        player.playSound(player.getLocation(), enumSound, 1, 1);
                    } catch (IllegalArgumentException e) {
                        lockedDimension.getLogger().warning(sound + " does not exist.");
                    }
                }
                if (locked != null && !locked.isEmpty()) {
                    player.sendMessage(Utils.color(locked));
                }
            }
        }
    }
    
    public void lock(Player player, String worldName){
        for(Dimension dimension : dimensions){
            Options options = dimension.getOptions();
            if (options.getWorldName().equalsIgnoreCase(worldName)) {
                String disabled = options.getLockedMessage();
                String sound = options.getLockedSound();
                if (lockedDimension.isEnabledPAPI()){
                    disabled = Utils.parse(player, options.getLockedMessage());
                }
                if (sound != null) {
                    try {
                        Sound enumSound = Sound.valueOf(sound);
                        player.playSound(player.getLocation(), enumSound, 1, 1);
                    } catch (IllegalArgumentException e) {
                        lockedDimension.getLogger().warning(sound + " does not exist.");
                    }
                }
                if (disabled != null && !disabled.isEmpty()) {
                    player.sendMessage(Utils.color(disabled));
                }
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
    
    public String getBroadcastMessage(){
        return config.getString("dimensions." + worldName + ".broadcast-message");
    }
    
    public String getSucceedSound(){
        return config.getString("dimensions." + worldName + ".succeed-sound");
    }
    public String getDeniedSound(){
        return config.getString("dimensions." + worldName + ".denied-sound");
    }
    public String getLockedSound(){
        return config.getString("dimensions." + worldName + ".locked-sound");
    }
    
    public static ArrayList<Dimension> getDimensions() {
        return dimensions;
    }
}
