package me.tofpu.lockeddimension.config.action.type.types;

import me.tofpu.lockeddimension.config.action.type.ActionType;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastType implements ActionType {
    @Override
    public void playAction(Player player, String[] args) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < args.length; i++){
            builder.append(args[i]).append(" ");
        }
        Bukkit.getServer().broadcastMessage(UtilsHelper.format(player, builder.toString()));
    }

    @Override
    public String getType() {
        return "[BROADCAST]";
    }
}
