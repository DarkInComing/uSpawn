package it.darksolutions.uspawn.listener;

import it.darksolutions.uspawn.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitMessageListener implements Listener {

    FileConfiguration data = Main.getInstance().getConfig();

    @EventHandler
    public void onJoin(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if(data.getBoolean("Quit.Enabled"))  { // true
            String messageFormat = data.getString("Quit.Message").replace("%player%", event.getPlayer().getName());
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', messageFormat));
        } else {
            event.setQuitMessage(null);
        }
    }
}
