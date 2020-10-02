package me.tofpu.lockeddimension.modules;

public class SoundBuilder {
    private String succeedSound;
    private String deniedSound;
    private String lockedSound;
    
    public SoundBuilder build(){
        return this;
    }
    
    public SoundBuilder setLockedSound(String lockedSound) {
        this.lockedSound = lockedSound;
        return this;
    }
    
    public SoundBuilder setDeniedSound(String deniedSound) {
        this.deniedSound = deniedSound;
        return this;
    }
    
    public SoundBuilder setSucceedSound(String succeedSound) {
        this.succeedSound = succeedSound;
        return this;
    }
    
    public String getLockedSound() {
        return lockedSound;
    }
    
    public String getDeniedSound() {
        return deniedSound;
    }
    
    public String getSucceedSound() {
        return succeedSound;
    }
}
