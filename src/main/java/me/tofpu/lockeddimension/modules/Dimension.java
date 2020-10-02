package me.tofpu.lockeddimension.modules;

import me.tofpu.lockeddimension.modules.Options;

public class Dimension {
    private final Options options;
    public Dimension(Options options){
        this.options = options;
    }
    
    public Options getOptions() {
        return options;
    }
}
