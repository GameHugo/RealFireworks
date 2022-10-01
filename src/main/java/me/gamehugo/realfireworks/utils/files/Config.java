package me.gamehugo.realfireworks.utils.files;

import me.gamehugo.realfireworks.RealFireworks;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class Config {

    private static File file;
    private static FileConfiguration config;

    public static void setup() {
        file = new File(RealFireworks.getInstance().getDataFolder(), "config.yml");
        if(!file.exists()) {
            File file = FileCreator.createFile("/Fireworks", "example.yml");
            if(file == null) return;
            FileConfiguration config = FileCreator.createConfig(file);
            config.setDefaults(FileCreator.getDefault("example.yml"));
            config.options().copyDefaults(true);
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RealFireworks.getInstance().reloadConfig();
        RealFireworks.getInstance().getConfig().options().copyDefaults(true);
        RealFireworks.getInstance().saveConfig();
        config = RealFireworks.getInstance().getConfig();
    }

    public static void reload() {
        RealFireworks.getInstance().reloadConfig();
    }

    public static void save() {
        RealFireworks.getInstance().saveConfig();
    }

    public static File getFile() {
        return file;
    }
    public static FileConfiguration getConfig() {
        return config;
    }
}
