package it.darksolutions.uspawn.utils.chat;

import org.bukkit.ChatColor;

/**
 * Created by Infames
 * Date: 04/04/2022 @ 19:03
 */
public class ColorUtils {

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
