package it.darksolutions.uspawn.commands;

import it.darksolutions.uspawn.uSpawn;
import it.darksolutions.uspawn.utils.Messages;
import it.darksolutions.uspawn.utils.TypeSerializations;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final uSpawn uSpawn;

    public SpawnCommand(uSpawn uSpawn) {
        this.uSpawn = uSpawn;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        FileConfiguration file = uSpawn.getInstance().getConfig();
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(uSpawn.getInstance().getMessagesTranslation().NO_CONSOLE);
            return true;
        }
        TypeSerializations typeSerializations = TypeSerializations.SPAWN;
        // Check before deserialization
        if(uSpawn.getConfig().getString(typeSerializations.getDirFile()).equals("")) {
            uSpawn.getLogger().info("No spawn location for " + p.getName());
            p.sendMessage(uSpawn.getInstance().getMessagesTranslation().NO_SPAWNPOINT);
            return true;
        }
        Location location = uSpawn.getLocationUtils().deserializeLocation(uSpawn, typeSerializations);

        p.teleport(location);

        return false;
    }

}
