package me.tofpu.lockeddimension.modules;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.UtilsHelper;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DimensionManager {
    private final LockedDimension lockedDimension;
    
    private FileConfiguration config;
    private final ConfigChecker configChecker;
    
    private static final String PATH = "dimensions.";
    protected String worldName = "";
    
    private static final List<Dimension> dimensions = new ArrayList<>();
    
    public DimensionManager(LockedDimension lockedDimension, FileConfiguration config){
        this.lockedDimension = lockedDimension;
        this.configChecker = new ConfigChecker(lockedDimension);
        
        this.config = config;
        for(String key : config.getConfigurationSection("dimensions").getKeys(false)) {
            worldName = key;
            configChecker.check(key, getSucceedSound(), getDeniedSound(), getLockedSound());

            Options options = new Options(key, isLocked(), getPermission(), getSucceedMessage(), getBroadcastMessage(), getDeniedMessage(), getLockedMessage(), getSucceedSound(), getDeniedSound(), getLockedSound());
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
                    success = UtilsHelper.parse(player, options.getSucceedMessage());
                }
                if (lockedDimension.isEnabledPAPI()){
                    broadcast = UtilsHelper.parse(player, options.getBroadcastMessage());
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
                    player.sendMessage(UtilsHelper.color(success));
                }
                if (broadcast != null && !broadcast.isEmpty()) {
                    player.sendMessage(UtilsHelper.color(broadcast));
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
                    locked = UtilsHelper.parse(player, options.getDeniedMessage());
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
                    player.sendMessage(UtilsHelper.color(locked));
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
                    disabled = UtilsHelper.parse(player, options.getLockedMessage());
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
                    player.sendMessage(UtilsHelper.color(disabled));
                }
            }
        }
    }
    
    public final boolean isLocked(){
        return config.getBoolean("dimensions." + worldName + ".lock");
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
    
    public static List<Dimension> getDimensions() {
        return dimensions;
    }
}