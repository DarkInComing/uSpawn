package it.darksolutions.uspawn.utils;

import it.darksolutions.uspawn.uSpawn;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class LocationUtils {

    public void serializeLocation(uSpawn uSpawn, @NonNull Location location, TypeSerializations typeSerializations) {
        FileConfiguration fileConfiguration = uSpawn.getConfig();
        try {
            fileConfiguration.set(typeSerializations.getDirFile(), location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getPitch() + ";" + location.getYaw() + ";" +  location.getWorld().getName());
        } catch (Exception exception) {

        }

    }

    public Location deserializeLocation(uSpawn uSpawn, TypeSerializations serializations) {
        try {
            String[] parts = uSpawn.getConfig().getString(serializations.getDirFile()).split(";");
            if(uSpawn.getConfig().getString(serializations.getDirFile()) == null) {
                uSpawn.getLogger().info("Error.");
                return null;
            }
            double x = Double.parseDouble(parts[0]);
            double y = Double.parseDouble(parts[1]);
            double z = Double.parseDouble(parts[2]);
            float pitch = Float.parseFloat(parts[3]);
            float yaw = Float.parseFloat(parts[4]);
            World world = uSpawn.getServer().getWorld(parts[5]);
            return new Location(world, x, y, z, yaw, pitch);
        } catch (Exception exception) {

        }
        return null;
    }
}
