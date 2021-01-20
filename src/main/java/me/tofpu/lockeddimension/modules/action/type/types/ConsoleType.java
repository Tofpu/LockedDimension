package me.tofpu.lockeddimension.modules.action.type.types;

import me.tofpu.lockeddimension.modules.action.type.ActionType;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ConsoleType implements ActionType {
    @Override
    public void playAction(Player player, String[] args) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < args.length; i++){
            builder.append(args[i]).append(" ");
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), UtilsHelper.format(player, builder.toString()));
    }

    @Override
    public String getType() {
        return "[CONSOLE]";
    }
}
