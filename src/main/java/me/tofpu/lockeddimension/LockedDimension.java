package me.tofpu.lockeddimension;

import me.tofpu.lockeddimension.commands.commands.Reload;
import me.tofpu.lockeddimension.commands.commands.module.CommandManager;
import me.tofpu.lockeddimension.modules.DimensionManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
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
    
        CommandManager manager = new CommandManager(this);
        PluginCommand pluginCommand = getCommand("lockeddimension");
        pluginCommand.setExecutor(manager);
        pluginCommand.setTabCompleter(manager);
        
        new Reload(this).register();
        
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
}
