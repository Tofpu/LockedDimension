package me.tofpu.lockeddimension.config.action.type;

import org.bukkit.entity.Player;

public interface ActionType {
    default void playAction(String[] args) {

    }

    default void playAction(Player player, String[] args){
        playAction(args);
    }

    String getType();
}
