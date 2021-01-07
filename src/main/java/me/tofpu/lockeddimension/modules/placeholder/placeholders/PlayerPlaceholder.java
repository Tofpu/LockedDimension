package me.tofpu.lockeddimension.modules.placeholder.placeholders;

import me.tofpu.lockeddimension.modules.placeholder.Placeholder;
import org.bukkit.entity.Player;

public class PlayerPlaceholder implements Placeholder {
    @Override
    public String getIdentifier() {
        return "player";
    }

    @Override
    public String getPlaceholder(Player player) {
        return player.getName();
    }
}
