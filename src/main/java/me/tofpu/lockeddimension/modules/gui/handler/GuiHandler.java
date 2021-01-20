package me.tofpu.lockeddimension.modules.gui.handler;

import me.tofpu.lockeddimension.modules.dimension.Dimension;
import me.tofpu.lockeddimension.modules.dimension.manager.DimensionManager;
import me.tofpu.lockeddimension.modules.gui.implemention.Gui;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Set;

public class GuiHandler {
    private static final Gui GUI = new Gui("Main GUI");

    public static void load(){
        Set<Dimension> dimensions = DimensionManager.getDimensions();

        Inventory inventory = Bukkit.createInventory(null, (dimensions.size() + 1) * 9);

        for (Dimension dimension : dimensions){
            ItemStack itemStack = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName(UtilsHelper.color(String.format("&d%s", dimension.getWorld().toUpperCase())));
            itemMeta.setLore(Arrays.asList(UtilsHelper.color(String.format("&5Click to modify &d%s&5!", dimension.getWorld().toUpperCase()))));

            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

            itemStack.setItemMeta(itemMeta);
            inventory.addItem(itemStack);
        }

        GUI.setInventory(inventory);
    }

    public static Gui getGui() {
        return GUI;
    }
}
