package me.gamehugo.realfireworks.Utils.Files;

import me.gamehugo.realfireworks.RealFireworks;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

@SuppressWarnings("unused")
public class Config {

    private static File file;
    private static FileConfiguration config;

    public static void setup() {
        file = new File(RealFireworks.getInstance().getDataFolder(), "config.yml");
        RealFireworks.getInstance().reloadConfig();
        RealFireworks.getInstance().getConfig().options().copyDefaults(true);
        RealFireworks.getInstance().saveConfig();
        config = RealFireworks.getInstance().getConfig();
    }

    public static void reload() {
        RealFireworks.getInstance().reloadConfig();
    }

    public static File getFile() {
        return file;
    }
    public static FileConfiguration getConfig() {
        return config;
    }
}
