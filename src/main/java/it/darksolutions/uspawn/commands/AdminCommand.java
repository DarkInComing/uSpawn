package it.darksolutions.uspawn.commands;

import it.darksolutions.uspawn.uSpawn;
import it.darksolutions.uspawn.utils.chat.ColorUtils;
import it.darksolutions.uspawn.utils.TypeSerializations;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class AdminCommand implements CommandExecutor {

    private final uSpawn spawn;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if (sender == null) {
            Bukkit.getConsoleSender().sendMessage(spawn.getMessagesTranslation().NO_CONSOLE);
            return true;
        }

        if (args.length == 0) {
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

        if (p.hasPermission("uspawn.admin") || p.isOp()) {
            switch (args[0]) {
                case "help":
                    p.sendMessage(" ");
                    p.sendMessage(" §2» §a/uspawn ");
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
                    break;
                case "setspawn":
                case "setfirstspawn":
                    Location loc = p.getLocation();
                    try {
                        spawn.getLocationUtils().serializeLocation(spawn, loc, TypeSerializations.FIRSTSPAWN);
                        spawn.saveConfig();
                        p.sendMessage(spawn.getMessagesTranslation().SUCCESS_SPAWNPOINT);
                    } catch (Exception exception) {
                        Bukkit.getConsoleSender().sendMessage("§cThere was an error while saving!");
                        p.sendMessage("§cThere was an error while saving!");
                    }
                    break;
                case "firstspawn":
                    TypeSerializations typeSerializations = TypeSerializations.FIRSTSPAWN;
                    if (spawn.getConfig().getString(typeSerializations.getDirFile()).equals("")) {
                        p.sendMessage(spawn.getMessagesTranslation().NO_FIRSTSPAWNPOINT);
                       // uSpawn.getInstance().getLogger().info("No firstspawn location for " + p.getName());
                    }

                    Location location = spawn.getLocationUtils().deserializeLocation(spawn, typeSerializations);

                    // Check firework
                    if (spawn.getConfig().getBoolean("firework.firstjoin")) {
                        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
                    }

                    p.teleport(location);
                    break;
            }
        } else {
            p.sendMessage(spawn.getMessagesTranslation().NO_PERM);
            p.sendMessage(ColorUtils.translate("&8» &6u&6&lSpawn &7by &aDarkInComing_"));
            p.sendMessage(ColorUtils.translate("§8» &aSourcecode on GitHub."));
        }
        return false;
    }


}
