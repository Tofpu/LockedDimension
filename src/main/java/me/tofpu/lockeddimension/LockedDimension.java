package me.tofpu.lockeddimension;

import me.tofpu.lockeddimension.updatechecker.SpigotUpdater;
import me.tofpu.lockeddimension.commands.Reload;
import me.tofpu.lockeddimension.commands.module.CommandManager;
import me.tofpu.lockeddimension.modules.DimensionManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class LockedDimension extends JavaPlugin {
    private DimensionManager manager;
    private boolean enabledPAPI;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        this.enabledPAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        this.manager = new DimensionManager(this, this.getConfig());
    
        int pluginId = 8999;
        new Metrics(this, pluginId);
    
        CommandManager manager = new CommandManager(this);
        PluginCommand pluginCommand = getCommand("lockeddimension");
        pluginCommand.setExecutor(manager);
        pluginCommand.setTabCompleter(manager);
        
        new Reload(this).register();
        
        Bukkit.getPluginManager().registerEvents(new SpigotUpdater(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPortalListener(this), this);
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public void reload(){
        reloadConfig();
        DimensionManager.getDimensions().clear();
        this.manager.reload(getConfig());
    }
    
    public DimensionManager getManager() {
        return manager;
    }
    
    public boolean isEnabledPAPI() {
        return enabledPAPI;
    }
}
