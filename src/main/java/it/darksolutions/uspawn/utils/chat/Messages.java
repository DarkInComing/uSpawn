package it.darksolutions.uspawn.utils.chat;

import it.darksolutions.uspawn.uSpawn;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

@RequiredArgsConstructor
public class Messages {

    private final uSpawn spawn;

    FileConfiguration file = spawn.getConfig();

    public String PREFIX = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix"));
    public String NO_PERM = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("No-perm"));
    public String SUCCESS_SPAWNPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("Success-spawnpoint"));
    public String NO_CONSOLE = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("Noconsole-command"));
    public String NO_FIRSTSPAWNPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("No-firstspawn"));
    public String NO_SPAWNPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("No-spawn"));
}
