package me.tofpu.lockeddimension.modules.action.type.types;

import me.tofpu.lockeddimension.modules.action.type.ActionType;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundType implements ActionType {
    @Override
    public void playAction(Player player, String[] args) {
        UtilsHelper.playSound(player, Sound.valueOf(args[1]));
    }

    @Override
    public String getType() {
        return "[SOUND]";
    }
}
