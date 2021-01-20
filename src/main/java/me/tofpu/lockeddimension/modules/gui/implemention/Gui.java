package me.tofpu.lockeddimension.modules.gui.implemention;

import me.tofpu.lockeddimension.modules.gui.IGui;
import org.bukkit.inventory.Inventory;

public class Gui implements IGui {
    private final String name;
    private Inventory inventory;

    public Gui(String name) {
        this.name = name;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
