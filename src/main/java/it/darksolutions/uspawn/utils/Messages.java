package it.darksolutions.uspawn.utils;

import it.darksolutions.uspawn.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Messages {

    static FileConfiguration file = Main.getInstance().getConfig();

    public static  String NO_PERM = ChatColor.translateAlternateColorCodes('&', file.getString("No-perm"));
    public static  String SUCCESS_SPAWNPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("Success-spawnpoint"));
    public static  String SUCCESS_FIRSTSPAWNPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("Success-firstspawnpoint"));
    public static String NO_CONSOLE = ChatColor.translateAlternateColorCodes('&', file.getString("Noconsole-command"));
    public static String NO_FIRSTPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("No-firstspawn"));
    public static String NO_SPAWNPOINT = ChatColor.translateAlternateColorCodes('&', file.getString("No-spawn"));
}
