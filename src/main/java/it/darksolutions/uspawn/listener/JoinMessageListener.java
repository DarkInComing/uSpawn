package it.darksolutions.uspawn.listener;

import it.darksolutions.uspawn.uSpawn;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMessageListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        FileConfiguration data = uSpawn.getInstance().getConfig();
        Player p = event.getPlayer();
        if(data.getBoolean("Join.Enabled"))  { // true
            String messageFormat = data.getString("Join.Message").replace("%player%", event.getPlayer().getName());
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', messageFormat));
        } else {
            event.setJoinMessage(null);
        }
    }
}
