package me.tofpu.lockeddimension.modules.dimension;

public class Dimension {
    private final String world;
    private DimensionValues dimensionValues;

    public Dimension(String world){
        this.world = world;
    }

    public DimensionValues getDimensionValues() {
        if (dimensionValues == null){
            dimensionValues = new DimensionValues();
        }
        return dimensionValues;
    }

    public String getWorld() {
        return world;
    }
}
