package it.darksolutions.uspawn;

import it.darksolutions.uspawn.Metrics.Metrics;
import it.darksolutions.uspawn.commands.AdminCommands;
import it.darksolutions.uspawn.commands.SpawnCommand;
import it.darksolutions.uspawn.listener.SpawnListeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

    private static Main instance;


    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        registerMetrics();
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, this);
        pm.registerEvents(new SpawnListeners(), this);
    }

    public void registerCommands() {
        getCommand("uspawn").setExecutor(new AdminCommands(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
    }

    public void registerMetrics() {
        int pluginId = 10317;
        Metrics metrics = new Metrics(this, pluginId);
    }





}
