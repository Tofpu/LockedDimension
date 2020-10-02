package me.tofpu.lockeddimension.modules;

public class Options {
    private String worldName;
    private boolean isLocked;
    private String permission;
    private String succeedMessage;
    private final String broadcastMessage;
    private String deniedMessage;
    private String lockedMessage;
    
    private final String succeedSound;
    private final String deniedSound;
    private final String lockedSound;
    
    public Options(String worldName, boolean isLocked, String permission, String succeedMessage, String broadcastMessage, String deniedMessage, String lockedMessage, String succeedSound, String deniedSound, String lockedSound){
        this.worldName = worldName;
        this.isLocked = isLocked;
        this.permission = permission;
        this.succeedMessage = succeedMessage;
        this.broadcastMessage = broadcastMessage;
        this.deniedMessage = deniedMessage;
        this.lockedMessage = lockedMessage;
        this.succeedSound = succeedSound;
        this.deniedSound = deniedSound;
        this.lockedSound = lockedSound;
    }
    
    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }
    
    public String getWorldName() {
        return worldName;
    }
    
    public void setLocked(boolean locked) {
        isLocked = locked;
    }
    
    public boolean isLocked() {
        return isLocked;
    }
    
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public String getPermission() {
        return permission;
    }
    
    public void setDeniedMessage(String lockedMessage) {
        this.deniedMessage = lockedMessage;
    }
    
    public String getDeniedMessage() {
        return deniedMessage;
    }
    
    public void setSucceedMessage(String succeedMessage) {
        this.succeedMessage = succeedMessage;
    }
    
    public String getSucceedMessage() {
        return succeedMessage;
    }
    
    public void setLockedMessage(String lockedMessage) {
        this.lockedMessage = lockedMessage;
    }
    
    public String getLockedMessage() {
        return lockedMessage;
    }
    
    public String getSucceedSound() {
        return succeedSound;
    }
    
    public String getDeniedSound() {
        return deniedSound;
    }
    
    public String getLockedSound() {
        return lockedSound;
    }
    
    public String getBroadcastMessage() {
        return broadcastMessage;
    }
}
