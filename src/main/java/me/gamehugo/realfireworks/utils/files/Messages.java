package me.gamehugo.realfireworks.utils.files;

import me.gamehugo.realfireworks.utils.Chat;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class Messages {

    private static File file;
    private static FileConfiguration config;

    public static void setup() {
        file = FileCreator.createFile("messages.yml");
        config = FileCreator.createConfig(file);
        config.setDefaults(FileCreator.getDefault("messages.yml"));
        config.options().copyDefaults(true);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String path) {
        if(getFileConfig().getString(path) != null) {
            return Chat.color(getFileConfig().getString(path));
        }
        return Chat.color("&cMessage not found");
    }

    public static File getFile() {
        return file;
    }
    public static FileConfiguration getFileConfig() {
        return config;
    }
}
