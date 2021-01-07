package me.tofpu.lockeddimension.modules.action.type.types;

import me.tofpu.lockeddimension.modules.action.type.ActionType;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.entity.Player;

public class TitleType implements ActionType {
    @Override
    public void playAction(Player player, String[] args) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < args.length; i++){
            builder.append(args[i]).append(" ");
        }
        String[] arg = builder.toString().split(":");

        player.sendTitle(UtilsHelper.format(player, arg[0]), UtilsHelper.format(player, arg[1]));
    }

    @Override
    public String getType() {
        return "[TITLE]";
    }
}
