package me.tofpu.lockeddimension.modules.action.type.register;

import me.tofpu.lockeddimension.modules.action.type.ActionType;
import me.tofpu.lockeddimension.modules.action.type.types.*;

import java.util.HashSet;
import java.util.Set;

public class TypeRegister {
    private static final Set<ActionType> ACTION_TYPES = new HashSet<>();

    static {
        register(new BroadcastType());
        register(new CommandType());
        register(new MessageType());
        register(new SoundType());
        register(new TitleType());
        register(new ConsoleType());
        register(new VelocityType());
    }

    public static void register(ActionType actionType){
        ACTION_TYPES.add(actionType);
    }

    public static void unregister(ActionType actionType){
        ACTION_TYPES.remove(actionType);
    }

    public static ActionType get(String actionType){
        for (ActionType type : ACTION_TYPES){
            if (type.getType().equals(actionType)){
                return type;
            }
        }
        return null;
    }

    public static Set<ActionType> getActionTypes() {
        return ACTION_TYPES;
    }
}
