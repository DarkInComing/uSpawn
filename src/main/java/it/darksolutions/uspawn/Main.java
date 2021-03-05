package it.darksolutions.uspawn;

import it.darksolutions.uspawn.Metrics.Metrics;
import it.darksolutions.uspawn.commands.AdminCommands;
import it.darksolutions.uspawn.commands.SpawnCommand;
import it.darksolutions.uspawn.listener.JoinMessageListener;
import it.darksolutions.uspawn.listener.QuitMessageListener;
import it.darksolutions.uspawn.listener.SpawnListeners;
import it.darksolutions.uspawn.versioncheck.VersionChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

    private static Main instance;
    public static boolean checkVersion;


    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        registerMetrics();
        registerTask();
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, this);
        pm.registerEvents(new SpawnListeners(), this);
        pm.registerEvents(new JoinMessageListener(), this);
        pm.registerEvents(new QuitMessageListener(), this);
    }

    public void registerCommands() {
        getCommand("uspawn").setExecutor(new AdminCommands(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
    }

    public void registerMetrics() {
        int pluginId = 10317;
        Metrics metrics = new Metrics(this, pluginId);
    }

    public void registerTask() {
        new VersionChecker(this, 89763).getVersion(version -> {
            if(this.getDescription().getVersion().equalsIgnoreCase(version))  {
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
            if(this.getDescription().getVersion().equalsIgnoreCase(version))  {
                checkVersion = true;
                p.sendMessage(prefix +  " §aThere is not a new update available!");
            } else {
                p.sendMessage(prefix + " §cThere is a new update available!");
                p.sendMessage( prefix + " §aLink: §fuspawn.darksolutions.it");
            }
        });
        return checkVersion;
    }





}
