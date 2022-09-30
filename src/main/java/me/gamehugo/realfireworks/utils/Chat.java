package me.gamehugo.realfireworks.utils;

public class Chat {

    /**
     * Change a string into colors
     *
     * @param message  The message that you want to translate to colors
     * @return  String with color's
     */
    public static String color(String message){
        return message.replace("&", "§");
    }
}
