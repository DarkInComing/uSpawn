package it.darksolutions.uspawn.listener;

import it.darksolutions.uspawn.uSpawn;
import it.darksolutions.uspawn.utils.TypeSerializations;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class SpawnListener implements Listener {

    private final uSpawn spawn;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player joiner = event.getPlayer();
        TypeSerializations typeSerializations;
        if (!joiner.hasPlayedBefore()) {
            typeSerializations = TypeSerializations.FIRSTSPAWN;

            // Check before deserialization
            if (spawn.getConfig().getString(typeSerializations.getDirFile()).equals("")) {
                spawn.getLogger().info("No firstspawn location for " + joiner.getName());
                return;
            }

            Location location = spawn.getLocationUtils().deserializeLocation(spawn, typeSerializations);

            // Check firework
            spawn.getServer().getScheduler().runTaskLater(spawn, () -> {
                if (spawn.getConfig().getBoolean("Firework.FirstJoin")) {
                    location.getWorld().spawnEntity(location, EntityType.FIREWORK);
                }
            }, 20L);

            joiner.teleport(location);
        } else {
            typeSerializations = TypeSerializations.SPAWN;

            // Check before deserialization
            if (spawn.getConfig().getString(typeSerializations.getDirFile()).equals("")) {
                spawn.getLogger().info("No firstspawn location for " + joiner.getName());
                return;
            }
            Location location = spawn.getLocationUtils().deserializeLocation(spawn, typeSerializations);

            joiner.teleport(location);

            spawn.getServer().getScheduler().runTaskLater(spawn, () -> {
                if (spawn.getConfig().getBoolean("Firework.NormalJoin")) {
                    location.getWorld().spawnEntity(location, EntityType.FIREWORK);
                }
            }, 20L);
        }
    }
}
