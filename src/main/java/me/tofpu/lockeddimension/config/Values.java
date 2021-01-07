package me.tofpu.lockeddimension.config;

import me.tofpu.lockeddimension.modules.dimension.manager.DimensionManager;
import org.bukkit.configuration.file.FileConfiguration;

public class Values {
    private final FileConfiguration fileConfiguration;
    private final String world;

    public Values(FileConfiguration fileConfiguration, String world) {
        this.fileConfiguration = fileConfiguration;
        this.world = world;
    }

    public static Values get(FileConfiguration fileConfiguration, String world){
        return new Values(fileConfiguration, world);
    }

    public boolean isLocked(){
        return fileConfiguration.getBoolean(String.format("%s.%s.lock", DimensionManager.getPath(), world));
    }

    public String getWorld() {
        return world;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }
}
