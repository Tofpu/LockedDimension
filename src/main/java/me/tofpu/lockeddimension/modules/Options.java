package me.tofpu.lockeddimension.modules;

import me.tofpu.lockeddimension.builders.MessageBuilder;
import me.tofpu.lockeddimension.builders.SoundBuilder;

public class Options {
    private String worldName;
    private boolean isLocked;
    private String permission;
    
    private MessageBuilder messageBuilder;
    private SoundBuilder soundBuilder;
    
    public Options build(){
        return this;
    }
    
    public Options setWorldName(String worldName) {
        this.worldName = worldName;
        return this;
    }
    
    public Options setPermission(String permission) {
        this.permission = permission;
        return this;
    }
    
    public Options setLocked(boolean locked) {
        isLocked = locked;
        return this;
    }
    
    public Options setMessageBuilder(MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
        return this;
    }
    
    public Options setSoundBuilder(SoundBuilder soundBuilder) {
        this.soundBuilder = soundBuilder;
        return this;
    }
    
    public String getWorldName() {
        return worldName;
    }
    
    public boolean isLocked() {
        return isLocked;
    }
    
    public String getPermission() {
        return permission;
    }
    
    public MessageBuilder getMessageBuilder() {
        return messageBuilder;
    }
    
    public SoundBuilder getSoundBuilder() {
        return soundBuilder;
    }
}
