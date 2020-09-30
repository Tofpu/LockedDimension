package me.tofpu.lockeddimension;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static String parse(Player player, String message){
        return PlaceholderAPI.setPlaceholders(player, message);
    }
}
