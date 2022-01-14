package me.gamehugo.realfireworks.Utils.Files;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.Utils.Chat;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Messages {

    private static FileConfiguration fileConfig;

    public static void setupFile() {
        File file = new File(RealFireworks.getInstance().getDataFolder(), "messages.yml");

        if (!file.exists()) {
            if(file.getParentFile().mkdirs()) {
                RealFireworks.getInstance().saveResource("messages.yml", false);
            }
        }

        fileConfig = new YamlConfiguration();

        try {
            fileConfig.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static String get(String path) {
        if(fileConfig.getString(path) != null) {
            return Chat.color(fileConfig.getString(path));
        }
        return Chat.color("&cMessage not found");
    }

}
