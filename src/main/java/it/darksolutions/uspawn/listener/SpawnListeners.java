package it.darksolutions.uspawn.listener;

import it.darksolutions.uspawn.uSpawn;
import it.darksolutions.uspawn.versioncheck.VersionChecker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpawnListeners implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        final Player p = event.getPlayer();
        FileConfiguration file = uSpawn.getInstance().getConfig();
        if(!p.hasPlayedBefore()) {
            if(!(uSpawn.getInstance().getConfig().getConfigurationSection("firstspawn").getKeys(false).isEmpty())) {
                try {
                    double X = file.getDouble("firstspawn.X");
                    double Y = file.getDouble("firstspawn.Y");
                    double Z = file.getDouble("firstspawn.Z");
                    float pitch = file.getInt("firstspawn.pitch");
                    float yaw = file.getInt("firstspawn.yaw");
                    String world = file.getString("firstspawn.world");
                    Location loc = new Location(Bukkit.getWorld(world), X, Y, Z, yaw, pitch);
                    p.teleport(loc);
                } catch (Exception exception) {
                    Bukkit.getConsoleSender().sendMessage("§cAn error occurred while loading data from the config. More information:");
                    exception.printStackTrace();
                }
            } else {
                Bukkit.getConsoleSender().sendMessage("§cNo firstspawn points have been registered for §4" + p.getName());
            }
        } else {
            if(!(uSpawn.getInstance().getConfig().getConfigurationSection("spawn").getKeys(false).isEmpty())) {
                try {
                    double X = file.getDouble("spawn.X");
                    double Y = file.getDouble("spawn.Y");
                    double Z = file.getDouble("spawn.Z");
                    float pitch = file.getInt("spawn.pitch");
                    float yaw = file.getInt("spawn.yaw");
                    String world = file.getString("spawn.world");
                    Location loc = new Location(Bukkit.getWorld(world), X, Y, Z, yaw, pitch);
                    p.teleport(loc);
                } catch (Exception exception) {
                    Bukkit.getConsoleSender().sendMessage("§cAn error occurred while loading data from the config. More information:");
                    exception.printStackTrace();
                }
            } else {
                Bukkit.getConsoleSender().sendMessage("§cNo spawn points have been registered for §4" + p.getName());
            }
        }
    }
}
