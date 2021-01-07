package me.tofpu.lockeddimension.modules.action.type;

import org.bukkit.entity.Player;

public interface ActionType {
    default void playAction(String[] args) {

    }

    default void playAction(Player player, String[] args){
        playAction(args);
    }

    String getType();
}
