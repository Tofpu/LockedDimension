package me.tofpu.lockeddimension.gui;

import me.tofpu.lockeddimension.modules.dimension.manager.DimensionManager;
import me.tofpu.lockeddimension.modules.gui.implemention.Gui;
import me.tofpu.utilitybuilders.item.ItemBuilder;
import me.tofpu.utilitybuilders.item.meta.ItemMetaBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainGui {
    private static final Gui gui = new Gui("LockedDimension Editor");
    private final static Set<Integer> SLOTS = new HashSet<>(Arrays.asList(11, 12, 13, 14, 15, 19, 20, 21, 22, 23 ,24 ,25, 28, 29, 30, 31, 32, 33 ,34 ,38, 39, 40, 41, 42));

    public static void load(){
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, "&5LockedDimension Editor");

        ItemStack backLayersMaterial = new ItemStack(Material.STAINED_GLASS_PANE);
        backLayersMaterial.setDurability((short) 2);

        ItemStack frontLayersMaterial = new ItemStack(Material.STAINED_GLASS_PANE);

        ItemStack extraMaterial = ItemBuilder.create().setType(Material.PAPER).setItemMetaBuilder(ItemMetaBuilder.create().setDisplayName(String.format("&d%d &5of &dDimensions available!", DimensionManager.getDimensions().size() + 1))).build();

        ItemStack slotsMaterial = new ItemStack(Material.STAINED_GLASS_PANE);
        slotsMaterial.setDurability((short) 15);

        Set<Integer> backLayers = new HashSet<>(Arrays.asList(0, 1, 9, 7, 8, 17, 36, 35, 45, 46, 44, 52, 53));
        Set<Integer> frontLayers = new HashSet<>(Arrays.asList(2, 3, 5, 6, 10, 16, 18, 26, 27, 35, 37, 43, 47, 48, 50, 51));
        Set<Integer> extra = new HashSet<>(Arrays.asList(4, 49));

        for (Integer integer : backLayers) inventory.setItem(integer, backLayersMaterial);

        for (Integer integer : frontLayers) inventory.setItem(integer, frontLayersMaterial);

        for (Integer integer : extra) inventory.setItem(integer, extraMaterial);

        for (Integer integer : SLOTS) inventory.setItem(integer, slotsMaterial);

        gui.setInventory(inventory);
    }

    public static Gui getGui() {
        return gui;
    }

    public static Set<Integer> getSlots() {
        return SLOTS;
    }
}
