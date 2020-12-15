package me.tofpu.lockeddimension;

import me.tofpu.lockeddimension.commands.Reload;
import me.tofpu.lockeddimension.commands.manager.CommandManager;
import me.tofpu.lockeddimension.config.updatechecker.SpigotUpdater;
import me.tofpu.lockeddimension.listeners.PlayerPortalListener;
import me.tofpu.lockeddimension.modules.manager.DimensionManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class LockedDimension extends JavaPlugin {
    private DimensionManager manager;
    private boolean enabledPAPI;
    
    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    
        int pluginId = 8999;
        new Metrics(this, pluginId);
    
        this.manager = new DimensionManager(this, this.getConfig()).init();
        this.enabledPAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        
        registerCommands();
        registerListeners();
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public void reload(){
        reloadConfig();
        this.manager.reload(getConfig());
    }
    
    public void registerCommands(){
        CommandManager manager = new CommandManager(this);
        PluginCommand pluginCommand = getCommand("lockeddimension");
        pluginCommand.setExecutor(manager);
        pluginCommand.setTabCompleter(manager);
    
        new Reload(this).register();
    }
    
    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new SpigotUpdater(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPortalListener(this), this);
    }
    
    public DimensionManager getManager() {
        return manager;
    }
    
    public boolean isEnabledPAPI() {
        return enabledPAPI;
    }
}
