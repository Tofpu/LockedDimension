package me.tofpu.lockeddimension.modules.gui.child.implemention;

import me.tofpu.lockeddimension.modules.gui.child.IChildGui;
import org.bukkit.inventory.ItemStack;

public class ChildGui implements IChildGui {
    private final String name;
    private final ItemStack itemStack;

    public ChildGui(String name, ItemStack itemStack) {
        this.name = name;
        this.itemStack = itemStack;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ItemStack getItem() {
        return itemStack;
    }
}
