package me.tofpu.lockeddimension.modules.action.actions;

import me.tofpu.lockeddimension.modules.action.Action;
import me.tofpu.lockeddimension.modules.dimension.manager.DimensionManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Locked implements Action {
    private final FileConfiguration fileConfiguration;
    private final String world;

    public Locked(FileConfiguration fileConfiguration, String world) {
        this.fileConfiguration = fileConfiguration;
        this.world = world;
    }

    @Override
    public List<String> getAction() {
        return fileConfiguration.getStringList(String.format("%s.%s.action.locked", DimensionManager.getPath(), world));
    }
}
