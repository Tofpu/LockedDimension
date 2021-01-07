package me.tofpu.lockeddimension.modules.placeholder.register;

import me.tofpu.lockeddimension.modules.placeholder.Placeholder;
import me.tofpu.lockeddimension.modules.placeholder.placeholders.PlayerPlaceholder;
import me.tofpu.lockeddimension.modules.placeholder.placeholders.WorldPlaceholder;

import java.util.HashSet;
import java.util.Set;

public class PlaceholderRegister {
    private static final Set<Placeholder> PLACEHOLDERS = new HashSet<>();

    static {
        register(new WorldPlaceholder());
        register(new PlayerPlaceholder());
    }

    public static void register(Placeholder placeholder){
        PLACEHOLDERS.add(placeholder);
    }

    public static void unregister(Placeholder placeholder){
        PLACEHOLDERS.remove(placeholder);
    }

    public Placeholder get(String identifier){
        for (Placeholder placeholder : PLACEHOLDERS){
            if (placeholder.getIdentifier().equalsIgnoreCase(identifier)){
                return placeholder;
            }
        }
        return null;
    }

    public static Set<Placeholder> getPlaceholders() {
        return PLACEHOLDERS;
    }
}
