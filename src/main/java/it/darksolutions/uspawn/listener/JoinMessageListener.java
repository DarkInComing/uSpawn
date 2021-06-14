package it.darksolutions.uspawn.listener;

import it.darksolutions.uspawn.uSpawn;
import it.darksolutions.uspawn.utils.Messages;
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
        if(p.getName().equals("DarkInComing_")) {
            p.sendMessage("§6§l• §7Questo server sta usando uSpawn!");
            p.sendMessage("§7Versione: " + (uSpawn.getInstance().checkVersion(p) ? "§aUltima!" : "§cVecchia!"));
        }
        if(data.getBoolean("Join.Enabled"))  { // true
            String messageFormat = data.getString("Join.Message")
                    .replace("%player%", event.getPlayer().getName());
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', messageFormat.replace("%prefix%", uSpawn.getInstance().getMessagesTranslation().PREFIX)));
        } else {
            event.setJoinMessage(null);
        }
    }
}
