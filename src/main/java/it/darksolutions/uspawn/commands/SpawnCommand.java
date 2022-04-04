package it.darksolutions.uspawn.commands;

import it.darksolutions.uspawn.uSpawn;
import it.darksolutions.uspawn.utils.TypeSerializations;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (sender == null) {
            Bukkit.getConsoleSender().sendMessage(uSpawn.getInstance().getMessagesTranslation().NO_CONSOLE);
            return true;
        }

        TypeSerializations typeSerializations = TypeSerializations.SPAWN;
        // Check before deserialization
        if (uSpawn.getInstance().getConfig().getString(typeSerializations.getDirFile()).equals("")) {
            uSpawn.getInstance().getLogger().info("No spawn location for " + p.getName());
            p.sendMessage(uSpawn.getInstance().getMessagesTranslation().NO_SPAWNPOINT);
            return true;
        }
        Location location = uSpawn.getInstance().getLocationUtils().deserializeLocation(uSpawn.getInstance(), typeSerializations);

        p.teleport(location);

        return false;
    }

}
