package me.tofpu.lockeddimension.modules.action.type.types;

import me.tofpu.lockeddimension.modules.action.type.ActionType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VelocityType implements ActionType {
    @Override
    public void playAction(Player player, String[] args) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < args.length; i++){
            builder.append(args[i]).append(" ");
        }
        String[] arg = builder.toString().split(" ");

        player.setVelocity(new Vector(parseInt(arg[0]), parseInt(arg[1]), parseInt(arg[2])));
    }

    @Override
    public String getType() {
        return "[VELOCITY]";
    }

    public int parseInt(String string){
        return Integer.parseInt(string);
    }
}
