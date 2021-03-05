package it.darksolutions.uspawn.commands;

import it.darksolutions.uspawn.Main;
import it.darksolutions.uspawn.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    Main main = Main.getInstance();
    FileConfiguration file = main.getConfig();

    public SpawnCommand(Main main) {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(Messages.NO_CONSOLE);
            return true;
        }
        if(file.getConfigurationSection("spawn").getKeys(false).isEmpty()) {
            p.sendMessage(Messages.NO_SPAWNPOINT);
        } else {
            try {
                double X = file.getDouble("spawn.X");
                double Y = file.getDouble("spawn.Y");
                double Z = file.getDouble("spawn.Z");
                float pitch = file.getInt("spawn.pitch");
                float yaw = file.getInt("spawn.yaw");
                String world = file.getString("spawn.world");
                Location loc = new Location(Bukkit.getWorld(world), X, Y, Z, yaw, pitch);
                p.teleport(loc);
            } catch (Exception exception) {
                Bukkit.getConsoleSender().sendMessage("§cThere was an error while saving!");
                exception.printStackTrace();
            }
        }
        return false;
    }

}
