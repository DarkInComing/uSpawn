package it.darksolutions.uspawn;

import it.darksolutions.uspawn.api.metrics.Metrics;
import it.darksolutions.uspawn.api.uSpawnAPI;
import it.darksolutions.uspawn.commands.*;
import it.darksolutions.uspawn.listener.*;
import it.darksolutions.uspawn.utils.*;
import it.darksolutions.uspawn.utils.VersionChecker;
import it.darksolutions.uspawn.utils.chat.Messages;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Getter
public class uSpawn extends JavaPlugin implements uSpawnAPI {

    @Getter private static uSpawn instance;

    private static boolean checkVersion;
    private LocationUtils utils;
    private Messages messages;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        registerMetrics();
        registerTask();
        this.utils = new LocationUtils();
        this.messages = new Messages();
    }

    private void registerListeners() {
        Arrays.asList(new Listener[] { new PlayerListener(), new SpawnListener() }).forEach(listener -> Bukkit.getServer().getPluginManager().registerEvents(listener, this));
    }

    public void registerCommands() {
        getCommand("uspawn").setExecutor(new AdminCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
    }

    public void registerMetrics() {
        int pluginId = 10317;
        new Metrics(this, pluginId);
    }

    public void registerTask() {
        new VersionChecker(this, 89763).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version))  {
                Bukkit.getConsoleSender().sendMessage("[uSpawn] §aThere is not a new update available!");
            } else {
                Bukkit.getConsoleSender().sendMessage( "[uSpawn] §cThere is a new update available!");
                Bukkit.getConsoleSender().sendMessage("[uSpawn] §aLink: §fuspawn.darksolutions.it");
            }
        });
    }

    public boolean checkVersion(Player p) {
        String prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Prefix"));

        new VersionChecker(this, 89763).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version))  {
                checkVersion = true;
                p.sendMessage(prefix +  " §aThere is not a new update available!");
            } else {
                p.sendMessage(prefix + " §cThere is a new update available!");
                p.sendMessage( prefix + " §aLink: §fuspawn.darksolutions.it");
            }
        });
        return checkVersion;
    }

    @Override
    public LocationUtils getLocationUtils() {
        return utils;
    }

    @Override
    public Messages getMessagesTranslation() {
        return messages;
    }
}
