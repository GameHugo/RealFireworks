package me.gamehugo.realfireworks.Utils;

import org.bukkit.ChatColor;

public class Chat {

    /**
     * Change a string into colors
     *
     * @param message  The message that you want to translate to colors
     * @return  String with color's
     */
    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
