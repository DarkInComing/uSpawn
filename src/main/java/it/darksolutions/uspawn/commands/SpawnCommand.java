package it.darksolutions.uspawn.commands;

import it.darksolutions.uspawn.uSpawn;
import it.darksolutions.uspawn.utils.TypeSerializations;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class SpawnCommand implements CommandExecutor {

    private final uSpawn spawn;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (sender == null) {
            Bukkit.getConsoleSender().sendMessage(spawn.getMessagesTranslation().NO_CONSOLE);
            return true;
        }

        TypeSerializations typeSerializations = TypeSerializations.SPAWN;
        // Check before deserialization
        if (spawn.getConfig().getString(typeSerializations.getDirFile()).equals("")) {
            spawn.getLogger().info("No spawn location for " + p.getName());
            p.sendMessage(spawn.getMessagesTranslation().NO_SPAWNPOINT);
            return true;
        }
        Location location = spawn.getLocationUtils().deserializeLocation(spawn, typeSerializations);

        p.teleport(location);

        return false;
    }

}
