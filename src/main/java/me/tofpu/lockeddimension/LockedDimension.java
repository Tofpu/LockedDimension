package me.tofpu.lockeddimension;

import me.tofpu.lockeddimension.modules.DimensionManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class LockedDimension extends JavaPlugin {
    private DimensionManager manager;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        this.manager = new DimensionManager(this.getConfig());
    
        int pluginId = 8999;
        new Metrics(this, pluginId);
        
        Bukkit.getPluginManager().registerEvents(new PlayerPortalListener(this), this);
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public DimensionManager getManager() {
        return manager;
    }
}
