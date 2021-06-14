package it.darksolutions.uspawn.listener;

import it.darksolutions.uspawn.uSpawn;
import it.darksolutions.uspawn.utils.TypeSerializations;
import it.darksolutions.uspawn.versioncheck.VersionChecker;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpawnListeners implements Listener {

    private uSpawn uSpawn;

    public SpawnListeners(uSpawn uSpawn) {
        this.uSpawn = uSpawn;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player joiner = event.getPlayer();
        TypeSerializations typeSerializations;
        if(!joiner.hasPlayedBefore()) {
            typeSerializations = TypeSerializations.FIRSTSPAWN;

            // Check before deserialization
            if(uSpawn.getConfig().getString(typeSerializations.getDirFile()).equals("")) {
                uSpawn.getLogger().info("No firstspawn location for " + joiner.getName());
                return;
            }

            Location location = uSpawn.getLocationUtils().deserializeLocation(uSpawn, typeSerializations);

            // Check firework
            uSpawn.getServer().getScheduler().runTaskLater(uSpawn, () -> {
                if(uSpawn.getConfig().getBoolean("Firework.FirstJoin")) {
                    location.getWorld().spawnEntity(location, EntityType.FIREWORK);
                }
            }, 20L);

            joiner.teleport(location);
        } else {
            typeSerializations = TypeSerializations.SPAWN;

            // Check before deserialization
            if(uSpawn.getConfig().getString(typeSerializations.getDirFile()).equals("")) {
                uSpawn.getLogger().info("No firstspawn location for " + joiner.getName());
                return;
            }
            Location location = uSpawn.getLocationUtils().deserializeLocation(uSpawn, typeSerializations);

            joiner.teleport(location);

            uSpawn.getServer().getScheduler().runTaskLater(uSpawn, () -> {
                if(uSpawn.getConfig().getBoolean("Firework.NormalJoin")) {
                    location.getWorld().spawnEntity(location, EntityType.FIREWORK);
                }
            }, 20L);
        }
    }
}
