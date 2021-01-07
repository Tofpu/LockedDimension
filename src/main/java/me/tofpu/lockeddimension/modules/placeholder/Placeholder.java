package me.tofpu.lockeddimension.modules.placeholder;

import org.bukkit.entity.Player;

public interface Placeholder {
    String getIdentifier();
    String getPlaceholder(Player player);
}
