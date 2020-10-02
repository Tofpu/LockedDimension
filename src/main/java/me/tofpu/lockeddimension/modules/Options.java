package me.tofpu.lockeddimension.modules;

public class Options {
    private String worldName;
    private boolean isLocked;
    private String permission;
//    private String succeedMessage;
//    private String broadcastMessage;
//    private String deniedMessage;
//    private String lockedMessage;

//    private String succeedSound;
//    private String deniedSound;
//    private String lockedSound;
    
    private MessageBuilder messageBuilder;
    private SoundBuilder soundBuilder;
    
//    public Options(String worldName, boolean isLocked, String permission, String succeedMessage, String broadcastMessage, String deniedMessage, String lockedMessage, String succeedSound, String deniedSound, String lockedSound){
//        this.worldName = worldName;
//        this.isLocked = isLocked;
//        this.permission = permission;
//        this.succeedMessage = succeedMessage;
//        this.broadcastMessage = broadcastMessage;
//        this.deniedMessage = deniedMessage;
//        this.lockedMessage = lockedMessage;
//        this.succeedSound = succeedSound;
//        this.deniedSound = deniedSound;
//        this.lockedSound = lockedSound;
//    }
    
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
    
    //    public Options setSucceedSound(String succeedSound) {
//        this.succeedSound = succeedSound;
//        return this;
//    }
//
//    public Options setDeniedSound(String deniedSound) {
//        this.deniedSound = deniedSound;
//        return this;
//    }
//
//    public Options setLockedSound(String lockedSound) {
//        this.lockedSound = lockedSound;
//        return this;
//    }
//
//    public String getSucceedSound() {
//        return succeedSound;
//    }
//
//    public String getDeniedSound() {
//        return deniedSound;
//    }
//
//    public String getLockedSound() {
//        return lockedSound;
//    }
    
    //    public Options setLockedMessage(String lockedMessage) {
//        this.lockedMessage = lockedMessage;
//        return this;
//    }
//
//    public Options setDeniedMessage(String deniedMessage) {
//        this.deniedMessage = deniedMessage;
//        return this;
//    }
    
//    public Options setSucceedMessage(String succeedMessage) {
//        this.succeedMessage = succeedMessage;
//        return this;
//    }
    
//    public Options setBroadcastMessage(String broadcastMessage) {
//        this.broadcastMessage = broadcastMessage;
//        return this;
//    }

//    public String getDeniedMessage() {
//        return deniedMessage;
//    }
//
//    public String getSucceedMessage() {
//        return succeedMessage;
//    }
//
//    public String getLockedMessage() {
//        return lockedMessage;
//    }
    
    //    public String getBroadcastMessage() {
//        return broadcastMessage;
//    }
}
