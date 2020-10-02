package me.tofpu.lockeddimension.builders;

public class MessageBuilder {
    private String succeedMessage;
    private String broadcastMessage;
    private String deniedMessage;
    private String lockedMessage;
    
    public MessageBuilder build(){
        return this;
    }
    
    public MessageBuilder setBroadcastMessage(String broadcastMessage) {
        this.broadcastMessage = broadcastMessage;
        return this;
    }
    public MessageBuilder setLockedMessage(String lockedMessage) {
        this.lockedMessage = lockedMessage;
        return this;
    }
    
    public MessageBuilder setSucceedMessage(String succeedMessage) {
        this.succeedMessage = succeedMessage;
        return this;
    }
    public MessageBuilder setDeniedMessage(String deniedMessage) {
        this.deniedMessage = deniedMessage;
        return this;
    }
    
    public String getLockedMessage() {
        return lockedMessage;
    }
    
    public String getSucceedMessage() {
        return succeedMessage;
    }
    
    public String getDeniedMessage() {
        return deniedMessage;
    }
    
    public String getBroadcastMessage() {
        return broadcastMessage;
    }
}
