package me.gamehugo.realfireworks.utils.files;

import me.gamehugo.realfireworks.RealFireworks;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("unused")
public class Messages {

    private static File file;
    private static FileConfiguration config;

    public Messages() {
        file = FileCreator.createFile("messages.yml");
        config = FileCreator.createConfig(file);
        config.setDefaults(FileCreator.getDefault("messages.yml"));
        config.options().copyDefaults(true);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Config.getConfig().getBoolean("Debug")) RealFireworks.getInstance().getLogger().info("Loaded messages file");
    }

    public void reload() {
        config = FileCreator.createConfig(file);
        config.setDefaults(FileCreator.getDefault("messages.yml"));
        config.options().copyDefaults(true);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String path) {
        if(getFileConfig().getString(path) != null) {
            return color(Objects.requireNonNull(getFileConfig().getString(path)));
        }
        if(Config.getConfig().getBoolean("Debug")) RealFireworks.getInstance().getLogger().severe("Message not found ["+path+"]");
        return color("&cMessage not found");
    }

    /**
     * Change a string into colors
     *
     * @param message  The message that you want to translate to colors
     * @return  String with color's
     */
    public static String color(String message){
        return message.replace("&", "§");
    }

    public File getFile() {
        return file;
    }
    public FileConfiguration getFileConfig() {
        return config;
    }
}
