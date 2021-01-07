package me.tofpu.lockeddimension.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.modules.placeholder.Placeholder;
import me.tofpu.lockeddimension.modules.placeholder.register.PlaceholderRegister;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class UtilsHelper {
    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static String parse(Player player, String message){
        return PlaceholderAPI.setPlaceholders(player, color(message));
    }

    public static String format(Player player, String content){
        return LockedDimension.isEnabledPAPI() ? setDefaultPlaceholders(player, parse(player, content)) : setDefaultPlaceholders(player, color(content));
    }

    public static String setDefaultPlaceholders(Player player, String content){
        for(Placeholder placeholder : PlaceholderRegister.getPlaceholders()){
            content = content.replace("%" + placeholder.getIdentifier() + "%", placeholder.getPlaceholder(player));
        }
        return content;
    }
    
    public static boolean hasPermission(Player player, String worldName){
        return player.hasPermission(String.format("lockeddimension.%s", worldName));
    }
    
    public static void playSound(Player player, Sound sound){
        player.playSound(player.getLocation(), sound, 1f, 1f);
    }

    public static String prefixColorize(String format) {
        return color("&8[&5Locked&dDimension&8]&r " + format);
    }
}
