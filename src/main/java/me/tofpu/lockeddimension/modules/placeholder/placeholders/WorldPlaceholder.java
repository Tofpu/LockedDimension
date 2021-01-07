package me.tofpu.lockeddimension.modules.placeholder.placeholders;

import me.tofpu.lockeddimension.modules.placeholder.Placeholder;
import org.bukkit.entity.Player;

public class WorldPlaceholder implements Placeholder {
    @Override
    public String getIdentifier() {
        return "world";
    }

    @Override
    public String getPlaceholder(Player player) {
        return player.getWorld().getName();
    }
}
