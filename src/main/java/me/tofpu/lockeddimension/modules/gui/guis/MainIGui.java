package me.tofpu.lockeddimension.modules.gui.guis;

import me.tofpu.lockeddimension.modules.gui.IGui;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class MainIGui implements IGui {
    private final String name;
    private final Inventory inventory;

    public MainIGui(){
        name = "Main Gui";
        inventory = Bukkit.createInventory(null, 9, UtilsHelper.color("&5" + name));
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
