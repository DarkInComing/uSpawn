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
import org.bukkit.entity.Player;

import java.util.Objects;

public class AdminCommands implements CommandExecutor {


    public AdminCommands(uSpawn uSpawn) {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration file = uSpawn.getInstance().getConfig();
        Player p = (Player) sender;
        String[] arguments = new String[]{"help", "setfirstspawn", "setspawn"};
        if(!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(uSpawn.getInstance().getMessagesTranslation().NO_CONSOLE);
            return true;
        }
        if(p.hasPermission("uspawn.admin") || p.isOp()) {
            if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
                p.sendMessage("");
                p.sendMessage(" §6u§6§lSpawn §7v." + uSpawn.getInstance().getDescription().getVersion() + (uSpawn.getInstance().checkVersion(p) ? " §8| §aLatest !" : " §8| §cOldest"));
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
            if(args[0].equalsIgnoreCase("setfirstspawn")) {
                Location loc = p.getLocation();
                try {
                    uSpawn.getInstance().getLocationUtils().serializeLocation(uSpawn.getInstance(), loc, TypeSerializations.FIRSTSPAWN);
                    uSpawn.getInstance().saveConfig();
                    p.sendMessage(uSpawn.getInstance().getMessagesTranslation().SUCCESS_FIRSTSPAWNPOINT);
                } catch (Exception exception) {
                    Bukkit.getConsoleSender().sendMessage("§cThere was an error while saving!");
                    p.sendMessage("§cThere was an error while saving!");
                }
            }
            if(args[0].equalsIgnoreCase("setspawn")) {
                Location loc = p.getLocation();
                try {
                    uSpawn.getInstance().getLocationUtils().serializeLocation(uSpawn.getInstance(), loc, TypeSerializations.SPAWN);
                    uSpawn.getInstance().saveConfig();
                    p.sendMessage(uSpawn.getInstance().getMessagesTranslation().SUCCESS_SPAWNPOINT);
                } catch (Exception exception) {
                    Bukkit.getConsoleSender().sendMessage("§cThere was an error while saving!");
                    p.sendMessage("§cThere was an error while saving!");
                }
            }
            if(args[0].equalsIgnoreCase("firstspawn")) {
                TypeSerializations typeSerializations = TypeSerializations.FIRSTSPAWN;
                if(uSpawn.getInstance().getConfig().getString(typeSerializations.getDirFile()).equals("")) {
                    uSpawn.getInstance().getLogger().info("No firstspawn location for " + p.getName());
                }

                Location location = uSpawn.getInstance().getLocationUtils().deserializeLocation(uSpawn.getInstance(), typeSerializations);

                // Check firework
                if(uSpawn.getInstance().getConfig().getBoolean("firework.firstjoin")) {
                    location.getWorld().spawnEntity(location, EntityType.FIREWORK);
                }

                p.teleport(location);
            }


        } else {
            p.sendMessage(uSpawn.getInstance().getMessagesTranslation().NO_PERM);
            p.sendMessage("§8» §6u§6§lSpawn §7by §adiscord.darksolutions.it");
            p.sendMessage("§7uspawn.darksolutions.it");
        }
        return false;
    }
}
