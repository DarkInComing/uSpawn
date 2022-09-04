package it.darksolutions.uspawn.listener;

import it.darksolutions.uspawn.uSpawn;
import it.darksolutions.uspawn.utils.TypeSerializations;
import it.darksolutions.uspawn.utils.chat.ColorUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final uSpawn spawn;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration data = spawn.getConfig();
        Player p = event.getPlayer();
        if (p.getName().equals("DarkInComing_")) {
            p.sendMessage("§6§l• §7Questo server sta usando uSpawn!");
        }

        if (data.getBoolean("Join.Enabled"))  { // true
            String messageFormat = data.getString("Join.Message")
                    .replace("%player%", event.getPlayer().getName());
            event.setJoinMessage(ColorUtils.translate(messageFormat.replace("%prefix%", spawn.getMessagesTranslation().PREFIX)));
        } else {
            event.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        FileConfiguration data = spawn.getConfig();
        if (data.getBoolean("Quit.Enabled")) { // true
            String messageFormat = data.getString("Quit.Message")
                    .replace("%player%", event.getPlayer().getName());
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', messageFormat.replace("%prefix%", spawn.getMessagesTranslation().PREFIX)));
        } else {
            event.setQuitMessage(null);
        }
    }

    @EventHandler
    public void onPlayerFall(EntityDamageEvent event) {
        /**
         * anti void method | fuck skidders like imfound lol
         */
        if (!(event.getEntity() instanceof Player))
            return;
        if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
            event.setCancelled(true);

            TypeSerializations typeSerializations = TypeSerializations.SPAWN;

            Location location = spawn.getLocationUtils().deserializeLocation(spawn, typeSerializations);

            event.getEntity().teleport(location);
        }
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL))
            event.setCancelled(true);
    }
}
