package me.tofpu.lockeddimension.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.tofpu.lockeddimension.modules.Dimension;
import me.tofpu.lockeddimension.modules.Options;
import me.tofpu.lockeddimension.modules.manager.DimensionManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class UtilsHelper {
    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static String parse(Player player, String message){
        return PlaceholderAPI.setPlaceholders(player, message);
    }
    
    public static boolean hasPermission(final Dimension dimension, Player player, String worldName){
        final Options options = dimension.getOptions();

        if (options.getPermission().isEmpty()) {
            return true;
        }

        return player.hasPermission(options.getPermission());
    }
    
    public static void playSound(Player player, String sound){
        XSound.play(player, sound);
    }

    public static String prefixColorize(String format) {
        return color("&8[&5Locked&dDimension&8]&r " + format);
    }
}
