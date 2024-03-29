package me.tofpu.lockeddimension;

import me.tofpu.lockeddimension.commands.Reload;
import me.tofpu.lockeddimension.commands.manager.CommandManager;
import me.tofpu.lockeddimension.listeners.PlayerJoinListener;
import me.tofpu.lockeddimension.listeners.PlayerPortalListener;
import me.tofpu.lockeddimension.modules.manager.DimensionManager;
import me.tofpu.spigotupdater.updated.Updater;
import me.tofpu.spigotupdater.updated.callback.CallBack;
import me.tofpu.spigotupdater.updated.utils.SpigotUtil;
import org.apache.commons.lang.Validate;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class LockedDimension extends JavaPlugin {
    private DimensionManager manager;
    private Updater updater;

    private boolean enabledPAPI;
    private String url;
    
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
    
        int pluginId = 8999;
        new Metrics(this, pluginId);

        this.updater = new Updater(this, 84331);
        updater.result(new CallBack() {
            @Override
            public void olderVersion(JavaPlugin javaPlugin, int currentVersion, int latestVersion) {
                super.olderVersion(javaPlugin, currentVersion, latestVersion);
                url = SpigotUtil.getResultAsync("https://raw.githubusercontent.com/Tofpu/LockedDimension/master/url");
                getLogger().log(Level.WARNING, String.format("You can download the latest version at: %s", url));
            }

            @Override
            public void isUpdated(JavaPlugin javaPlugin, int latestVersion) {
                super.isUpdated(javaPlugin, latestVersion);
            }
        });

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
        final CommandManager manager = new CommandManager(this);
        PluginCommand pluginCommand = getCommand("lockeddimension");
        Validate.notNull(pluginCommand, "lockeddimension command cannot be null (plugin.yml corrupted?)");
        pluginCommand.setExecutor(manager);
        pluginCommand.setTabCompleter(manager);
    
        new Reload(this).register();
    }
    
    public void registerListeners(){
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(this), this);
        pluginManager.registerEvents(new PlayerPortalListener(this), this);
    }

    public String getUrl() {
        return url;
    }

    public boolean isEnabledPAPI() {
        return enabledPAPI;
    }

    public Updater getUpdater() {
        return updater;
    }

    public DimensionManager getManager() {
        return manager;
    }
}
