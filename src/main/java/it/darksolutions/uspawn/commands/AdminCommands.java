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

import java.util.Objects;

public class AdminCommands implements CommandExecutor {

    Main main = Main.getInstance();
    FileConfiguration file = main.getConfig();

    public AdminCommands(Main main) {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(Messages.NO_CONSOLE);
            return true;
        }
        if(p.hasPermission("uspawn.admin") || p.isOp()) {
            if(args.length == 0) {
                p.sendMessage(" §6u§6§lSpawn §7v." + main.getDescription().getVersion());
                p.sendMessage(" §b§ndiscord.darksolutions.it");
                p.sendMessage(" ");
                p.sendMessage(" §2» §a/uspawn help");
                p.sendMessage(" §8➥ §fGet this page!");
                p.sendMessage(" ");
                p.sendMessage(" §2» §a/uspawn setfirstspawn");
                p.sendMessage(" §8➥ §fUse this command to set the first spawn to");
                p.sendMessage(" §8➥ §fyour current location!");
                p.sendMessage(" ");
                p.sendMessage(" §2» §a/uspawn setspawn");
                p.sendMessage(" §8➥ §fUse this command to set the spawn point to");
                p.sendMessage(" §8➥ §f your current location!");
                p.sendMessage(" ");
                p.sendMessage(" §2» §a/spawn");
                p.sendMessage(" §8➥ §fEasy!");
                return true;
            }
            if(args[0].equalsIgnoreCase("help")) {
                p.sendMessage(" §6u§6§lSpawn §7v." + main.getDescription().getVersion());
                p.sendMessage(" §fdiscord.darksolutions.it");
                p.sendMessage(" ");
                p.sendMessage(" §2» §a/uspawn help");
                p.sendMessage(" §8• §fGet this page!");
                p.sendMessage(" ");
                p.sendMessage(" §2» §a/uspawn setfirstspawn");
                p.sendMessage(" §8• §fUse this command to set the first");
                p.sendMessage(" §8• §fspawn to your current location!");
                p.sendMessage(" ");
                p.sendMessage(" §2» §a/uspawn setspawn");
                p.sendMessage(" §8• §fUse this command to set the spawn");
                p.sendMessage(" §8• §fpoint to your current location!");
                p.sendMessage(" ");
                p.sendMessage(" §2» §a/spawn");
                p.sendMessage(" §8• §fEasy!");
                return true;
            }
            if(args[0].equalsIgnoreCase("setfirstspawn")) {
                Location loc = p.getLocation();
                int X = loc.getBlockX();
                int Y = loc.getBlockY();
                int Z = loc.getBlockZ();
                float pitch = loc.getPitch();
                float yaw = loc.getYaw();
                String world = Objects.requireNonNull(loc.getWorld().getName());
                try {
                    file.set("firstspawn.X", Double.valueOf(X) + 0.5);
                    file.set("firstspawn.Y", Double.valueOf(Y) + 0.5);
                    file.set("firstspawn.Z", Double.valueOf(Z) + 0.5);
                    file.set("firstspawn.pitch", Float.valueOf(pitch));
                    file.set("firstspawn.yaw", Float.valueOf(yaw));
                    file.set("firstspawn.world", String.valueOf(world));
                    main.saveConfig();
                    p.sendMessage(Messages.SUCCESS_FIRSTSPAWNPOINT);
                } catch (Exception exception) {
                    Bukkit.getConsoleSender().sendMessage("§cThere was an error while saving!");
                    exception.printStackTrace();
                }
            }
            if(args[0].equalsIgnoreCase("setspawn")) {
                Location loc = p.getLocation();
                int X = loc.getBlockX();
                int Y = loc.getBlockY();
                int Z = loc.getBlockZ();
                float pitch = loc.getPitch();
                float yaw = loc.getYaw();
                String world = Objects.requireNonNull(loc.getWorld().getName());
                try {
                    file.set("spawn.X", Double.valueOf(X) + 0.5);
                    file.set("spawn.Y", Double.valueOf(Y) + 0.5);
                    file.set("spawn.Z", Double.valueOf(Z) + 0.5);
                    file.set("spawn.pitch", Float.valueOf(pitch));
                    file.set("spawn.yaw", Float.valueOf(yaw));
                    file.set("spawn.world", String.valueOf(world));
                    main.saveConfig();
                    Bukkit.getWorld(world).setSpawnLocation(loc);
                    p.sendMessage(Messages.SUCCESS_SPAWNPOINT);
                } catch (Exception exception) {
                    Bukkit.getConsoleSender().sendMessage("§cThere was an error while saving!");
                    exception.printStackTrace();
                }
            }
        } else {
            p.sendMessage(Messages.NO_PERM);
            p.sendMessage("§8» §6u§6§lSpawn §7by §adiscord.darksolutions.it");
        }
        return false;
    }
}
