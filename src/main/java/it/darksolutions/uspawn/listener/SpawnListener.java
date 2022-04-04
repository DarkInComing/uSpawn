package it.darksolutions.uspawn.listener;

import it.darksolutions.uspawn.uSpawn;
import it.darksolutions.uspawn.utils.TypeSerializations;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpawnListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player joiner = event.getPlayer();
        TypeSerializations typeSerializations;
        if (!joiner.hasPlayedBefore()) {
            typeSerializations = TypeSerializations.FIRSTSPAWN;

            // Check before deserialization
            if (uSpawn.getInstance().getConfig().getString(typeSerializations.getDirFile()).equals("")) {
                uSpawn.getInstance().getLogger().info("No firstspawn location for " + joiner.getName());
                return;
            }

            Location location =uSpawn.getInstance().getLocationUtils().deserializeLocation(uSpawn.getInstance(), typeSerializations);

            // Check firework
            uSpawn.getInstance().getServer().getScheduler().runTaskLater(uSpawn.getInstance(), () -> {
                if (uSpawn.getInstance().getConfig().getBoolean("Firework.FirstJoin")) {
                    location.getWorld().spawnEntity(location, EntityType.FIREWORK);
                }
            }, 20L);

            joiner.teleport(location);
        } else {
            typeSerializations = TypeSerializations.SPAWN;

            // Check before deserialization
            if (uSpawn.getInstance().getConfig().getString(typeSerializations.getDirFile()).equals("")) {
                uSpawn.getInstance().getLogger().info("No firstspawn location for " + joiner.getName());
                return;
            }
            Location location = uSpawn.getInstance().getLocationUtils().deserializeLocation(uSpawn.getInstance(), typeSerializations);

            joiner.teleport(location);

            uSpawn.getInstance().getServer().getScheduler().runTaskLater(uSpawn.getInstance(), () -> {
                if (uSpawn.getInstance().getConfig().getBoolean("Firework.NormalJoin")) {
                    location.getWorld().spawnEntity(location, EntityType.FIREWORK);
                }
            }, 20L);
        }
    }
}
