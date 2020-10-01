package me.tofpu.lockeddimension.updatechecker;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

// HUGE THANKS TO PlaceholderAPI for this code! (https://github.com/PlaceholderAPI/PlaceholderAPI/blob/master/src/main/java/me/clip/placeholderapi/updatechecker/UpdateChecker.java)
public class SpigotUpdater implements Listener {
    private final LockedDimension lockedDimension;
    private String latestVersion;
    private boolean updateAvailable;
    
    private final String RESOURCE = "84331";
    
    public SpigotUpdater(LockedDimension lockeddimension){
        this.lockedDimension = lockeddimension;
        if (notifyUpdates()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    HttpsURLConnection connection = null;
                    try {
                        connection = (HttpsURLConnection) new URL(
                                "https://api.spigotmc.org/legacy/update.php?resource=" + RESOURCE
                        ).openConnection();
                        connection.setRequestMethod("GET");
                        latestVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            
                    if (latestVersion == null || latestVersion.isEmpty()) {
                        return;
                    }
            
                    updateAvailable = isUpdateAvailable();
                    if (!updateAvailable) {
                        return;
                    }
            
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            lockeddimension.getLogger().info("An update for LockedDimension (v" + latestVersion + ") is available at:");
                            lockeddimension.getLogger().info("https://www.spigotmc.org/resources/lockeddimension-1-8-8-1-16-3-have-freedom-over-your-dimensions." + RESOURCE);
                        }
                    }.runTask(lockeddimension);
                }
            }.runTaskAsynchronously(lockeddimension);
        }
    }
    
    public boolean isUpdateAvailable(){
        String pluginVersion = toReadable(lockedDimension.getDescription().getVersion());
        String latest = toReadable(latestVersion);
        
        return pluginVersion.compareTo(latest) < 0;
    }
    
    private String toReadable(String version) {
        if (version.contains("-SNAPSHOT")) {
            version = version.split("-SNAPSHOT")[0];
        }
        
        return version.replaceAll("\\.", "");
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (notifyUpdates()) {
            if (updateAvailable && e.getPlayer().hasPermission("lockeddimension.notify")) {
                String message = String.format("&dAn update for &5Locked&dDimension &7(&5v&d%s&7) &dis available at &5https://www.spigotmc.org/resources/lockeddimension-1-8-8-1-16-3-have-freedom-over-your-dimensions.%s", latestVersion, RESOURCE);
                e.getPlayer().sendMessage(Utils.color(message));
            }
        }
    }
    
    public boolean notifyUpdates(){
        return lockedDimension.getConfig().getBoolean("settings.notify-updates");
    }
}
