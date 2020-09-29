package me.tofpu.lockeddimension.modules;

import me.tofpu.lockeddimension.modules.Options;

public class Dimension {
    private Options options;
    public Dimension(Options options){
        this.options = options;
    }
    
    public void setOptions(Options options) {
        this.options = options;
    }
    
    public Options getOptions() {
        return options;
    }
}
