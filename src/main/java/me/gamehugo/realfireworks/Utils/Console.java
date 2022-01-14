package me.gamehugo.realfireworks.Utils;

import org.bukkit.Bukkit;

public class Console {

    /**
     * Send a message into the console.
     *
     * @param message  The message that is going to be sent in the console.
     */
    public static void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("[RealFireworks] "+Chat.color(message));
    }

    /**
     * Send a warning message into the console.
     *
     * @param message  The message that is going to be sent in the console.
     */
    public static void sendWarningMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("[RealFireworks] "+Chat.color("&e[WARNING] "+message));
    }

    /**
     * Send a command into the console.
     *
     * @param command  The command that is going to be sent in the console.
     */
    public static void sendCommand(String command) {
        if(command.substring(0, 1).contains("/")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.substring(1));
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }
}
