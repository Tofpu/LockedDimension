package me.tofpu.lockeddimension.modules;

public class Options {
    private String worldName = null;
    private boolean isLocked = false;
    private String permission = null;
    private String deniedMessage = null;
    private String succeedMessage = null;
    private String lockedMessage = null;
    
    public Options(String worldName, boolean isLocked, String permission, String deniedMessage, String succeedMessage, String lockedMessage){
        this.worldName = worldName;
        this.isLocked = isLocked;
        this.permission = permission;
        this.deniedMessage = deniedMessage;
        this.succeedMessage = succeedMessage;
        this.lockedMessage = lockedMessage;
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
}
