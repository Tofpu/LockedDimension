package me.tofpu.lockeddimension.config.action.type.register;

import me.tofpu.lockeddimension.config.action.type.ActionType;
import me.tofpu.lockeddimension.config.action.type.types.BroadcastType;
import me.tofpu.lockeddimension.config.action.type.types.CommandType;
import me.tofpu.lockeddimension.config.action.type.types.MessageType;
import me.tofpu.lockeddimension.config.action.type.types.SoundType;

import java.util.HashSet;
import java.util.Set;

public class TypeRegister {
    private static final Set<ActionType> ACTION_TYPES = new HashSet<>();

    static {
        register(new BroadcastType());
        register(new CommandType());
        register(new MessageType());
        register(new SoundType());
    }

    public static void register(ActionType actionType){
        ACTION_TYPES.add(actionType);
    }

    public static void unregister(ActionType actionType){
        ACTION_TYPES.remove(actionType);
    }

    public static ActionType get(String actionType){
        for (ActionType type : ACTION_TYPES){
            if (type.getType().equalsIgnoreCase(actionType)){
                return type;
            }
        }
        return null;
    }

    public static Set<ActionType> getActionTypes() {
        return ACTION_TYPES;
    }
}
