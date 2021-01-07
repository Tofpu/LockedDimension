package me.tofpu.lockeddimension.modules.dimension.manager;

import me.tofpu.lockeddimension.config.Values;
import me.tofpu.lockeddimension.modules.dimension.Dimension;
import me.tofpu.lockeddimension.modules.dimension.values.DimensionValues;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;

public class DimensionManager {
    private FileConfiguration fileConfiguration;

    private static final Set<Dimension> DIMENSIONS = new HashSet<>();
    private static final String PATH = "dimensions";

    public DimensionManager(FileConfiguration fileConfiguration) {
        reload(fileConfiguration);
    }

    public void reload(FileConfiguration fileConfiguration){
        DIMENSIONS.clear();
        this.fileConfiguration = fileConfiguration;

        for (String key : fileConfiguration.getConfigurationSection("dimensions").getKeys(false)){
            Values values = Values.get(fileConfiguration, key);

            Dimension dimension = new Dimension(key);
            DimensionValues dimensionValues = dimension.getDimensionValues();
            dimensionValues.setValues(values);

            DIMENSIONS.add(dimension);
        }
    }

    public Dimension getDimension(String world) {
        for (Dimension dimension : DIMENSIONS){
            if (dimension.getWorld().equalsIgnoreCase(world)){
                return dimension;
            }
        }
        return null;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public static Set<Dimension> getDimensions() {
        return DIMENSIONS;
    }

    public static String getPath() {
        return PATH;
    }
}
