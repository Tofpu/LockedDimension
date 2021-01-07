package me.tofpu.lockeddimension.config.action.type.types;

import me.tofpu.lockeddimension.config.action.type.ActionType;
import org.bukkit.entity.Player;

public class CommandType implements ActionType {
    @Override
    public void playAction(Player player, String[] args) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < args.length; i++){
            builder.append(args[i]).append(" ");
        }
        player.performCommand(builder.toString());
    }

    @Override
    public String getType() {
        return "[COMMAND]";
    }
}
