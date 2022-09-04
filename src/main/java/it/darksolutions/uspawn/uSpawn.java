package it.darksolutions.uspawn;

import it.darksolutions.uspawn.api.uSpawnAPI;
import it.darksolutions.uspawn.commands.*;
import it.darksolutions.uspawn.listener.*;
import it.darksolutions.uspawn.utils.*;
import it.darksolutions.uspawn.utils.chat.Messages;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Getter
public class uSpawn extends JavaPlugin implements uSpawnAPI {

    private LocationUtils utils;
    private Messages messages;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        this.utils = new LocationUtils();
        this.messages = new Messages(this);
    }

    private void registerListeners() {
        Arrays.asList(new Listener[] { new PlayerListener(this), new SpawnListener(this) }).forEach(listener -> Bukkit.getServer().getPluginManager().registerEvents(listener, this));
    }

    public void registerCommands() {
        getCommand("uspawn").setExecutor(new AdminCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
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
