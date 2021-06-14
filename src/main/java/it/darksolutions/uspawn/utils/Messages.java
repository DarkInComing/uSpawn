package it.darksolutions.uspawn.utils;

import it.darksolutions.uspawn.uSpawn;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Messages {

    static FileConfiguration file = uSpawn.getInstance().getConfig();

    public String PREFIX = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix"));
    public   String NO_PERM = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("No-perm"));
    public   String SUCCESS_SPAWNPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("Success-spawnpoint"));
    public String SUCCESS_FIRSTSPAWNPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("Success-firstspawnpoint"));
    public String NO_CONSOLE = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("Noconsole-command"));
    public String NO_FIRSTPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("No-firstspawn"));
    public String NO_SPAWNPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("Prefix") + " " + file.getString("No-spawn"));
}
