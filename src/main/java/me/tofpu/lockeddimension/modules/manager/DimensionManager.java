package me.tofpu.lockeddimension.modules.manager;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.builders.MessageBuilder;
import me.tofpu.lockeddimension.builders.SoundBuilder;
import me.tofpu.lockeddimension.config.ConfigChecker;
import me.tofpu.lockeddimension.config.ConfigValues;
import me.tofpu.lockeddimension.modules.Dimension;
import me.tofpu.lockeddimension.modules.Options;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DimensionManager {
    private final LockedDimension lockedDimension;

    private static final List<Dimension> dimensions = new ArrayList<>();
    private static final String PATH = "dimensions.";

    private FileConfiguration config;
    private final ConfigChecker configChecker;
    private final ConfigValues configValues;
    
    public DimensionManager(LockedDimension lockedDimension, FileConfiguration config){
        this.lockedDimension = lockedDimension;
        this.config = config;

        this.configChecker = new ConfigChecker(lockedDimension);
        this.configValues = new ConfigValues(config);
    }
    
    public DimensionManager init() {
        reload(config);
        return this;
    }
    
    public void reload(FileConfiguration fileConfiguration){
        dimensions.clear();
        this.config = fileConfiguration;
        this.configValues.setConfig(config);
        for(String key : lockedDimension.getConfig().getConfigurationSection("dimensions").getKeys(false)) {
            configValues.setWorldName(key);

            configChecker.check(key, configValues.getSucceedSound(), configValues.getDeniedSound(), configValues.getLockedSound());

            Options options = getOptions(key, this.configValues);
            dimensions.add(new Dimension(options));
        }
    }
    
    public void success(Player player, String worldName) {
        Dimension dimension = getDimension(worldName);
        if (dimension == null) {
            return;
        }
        Options options = dimension.getOptions();
        MessageBuilder messageBuilder = options.getMessageBuilder();
        SoundBuilder soundBuilder = options.getSoundBuilder();
    
        String success = messageBuilder.getSucceedMessage();
        String broadcast = messageBuilder.getBroadcastMessage();
        String sound = soundBuilder.getSucceedSound();
    
        try {
            UtilsHelper.playSound(player, Sound.valueOf(sound));
        } catch (IllegalArgumentException e) {
            lockedDimension.getLogger().warning(sound + " does not exist.");
        }
        
        if (broadcast != null && !broadcast.isEmpty()) {
            player.sendMessage(UtilsHelper.color(lockedDimension.isEnabledPAPI() ? UtilsHelper.parse(player, broadcast) : broadcast));
        }
        
        if (success != null && !success.isEmpty()) {
            player.sendMessage(UtilsHelper.color(lockedDimension.isEnabledPAPI() ? UtilsHelper.parse(player, success) : success));
        }
    }
    
    public void deny(Player player, String worldName) {
        Dimension dimension = getDimension(worldName);
        if (dimension == null) {
            return;
        }
        Options options = dimension.getOptions();
        MessageBuilder messageBuilder = options.getMessageBuilder();
        SoundBuilder soundBuilder = options.getSoundBuilder();
    
        String locked = messageBuilder.getDeniedMessage();
        String sound = soundBuilder.getDeniedSound();
        
        try {
            UtilsHelper.playSound(player, Sound.valueOf(sound));
        } catch (IllegalArgumentException e) {
            lockedDimension.getLogger().warning(sound + " does not exist.");
        }
        
        if (locked != null && !locked.isEmpty()) {
            player.sendMessage(UtilsHelper.color(lockedDimension.isEnabledPAPI() ? UtilsHelper.parse(player, locked) : locked));
        }
    }
    
    public void lock(Player player, String worldName) {
        Dimension dimension = getDimension(worldName);
        if (dimension == null) {
            return;
        }
        Options options = dimension.getOptions();
        MessageBuilder messageBuilder = options.getMessageBuilder();
        SoundBuilder soundBuilder = options.getSoundBuilder();
    
        String disabled = messageBuilder.getLockedMessage();
        String sound = soundBuilder.getLockedSound();
    
        try {
            UtilsHelper.playSound(player, Sound.valueOf(sound));
        } catch (IllegalArgumentException e) {
            lockedDimension.getLogger().warning(sound + " does not exist.");
        }
        
        if (disabled != null && !disabled.isEmpty()) {
            player.sendMessage(UtilsHelper.color(lockedDimension.isEnabledPAPI() ? UtilsHelper.parse(player, disabled) : disabled));
        }
    }
    public Options getOptions(String key, ConfigValues values){
        values.setWorldName(key);
        return new Options()
                .setWorldName(key)
                .setLocked(values.isLocked())
                .setPermission(values.getPermission())
                .setMessageBuilder(new MessageBuilder()
                        .setSucceedMessage(values.getSucceedMessage())
                        .setBroadcastMessage(values.getBroadcastMessage())
                        .setDeniedMessage(values.getDeniedMessage())
                        .setLockedMessage(values.getLockedMessage())
                        .build())
                .setSoundBuilder(new SoundBuilder()
                        .setSucceedSound(values.getSucceedSound())
                        .setDeniedSound(values.getDeniedSound())
                        .setLockedSound(values.getLockedSound())
                        .setLockedSound(values.getLockedSound())
                        .build())
                .build();
    }
    
    public Dimension getDimension(String worldName){
        for(Dimension dimension : dimensions) {
            if (dimension.getOptions().getWorldName().equalsIgnoreCase(worldName)) {
                return dimension;
            }
        }
        return null;
    }
    
    public static List<Dimension> getDimensions() {
        return dimensions;
    }
    
    public static String getPATH() {
        return PATH;
    }
}
