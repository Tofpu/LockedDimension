package me.tofpu.lockeddimension;

import me.clip.placeholderapi.PlaceholderAPI;
import me.tofpu.lockeddimension.modules.Dimension;
import me.tofpu.lockeddimension.modules.DimensionManager;
import me.tofpu.lockeddimension.modules.Options;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class UtilsHelper {
    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static String parse(Player player, String message){
        return PlaceholderAPI.setPlaceholders(player, message);
    }
    
    public static boolean hasPermission(Player player, String worldName){
        for(Dimension dimension : DimensionManager.getDimensions()){
            Options options = dimension.getOptions();
            if (options.getWorldName().equalsIgnoreCase(worldName)) {
                return player.hasPermission(options.getPermission());
            }
        }
        return false;
    }
}
